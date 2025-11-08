package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicInvitationClinicDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationDoctorDTO;
import com.healthpro.clinicservice.entity.ClinicInvitation;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.entity.enums.InvitationStatus;
import com.healthpro.clinicservice.exception.ClinicSpecialtyHasNoScheduleException;
import com.healthpro.clinicservice.exception.InvitationAlreadyAcceptedException;
import com.healthpro.clinicservice.mapper.ClinicInvitationDTOMapper;
import com.healthpro.clinicservice.repository.ClinicInvitationRepository;
import com.healthpro.clinicservice.repository.ClinicSpecialtyRepository;
import com.healthpro.clinicservice.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClinicInvitationService {
    private final ClinicInvitationRepository clinicInvitationRepository;
    private final ClinicSpecialtyRepository clinicSpecialtyRepository;
    private final ClinicSpecialtyDoctorService clinicSpecialtyDoctorService;
    private final DoctorRepository doctorRepository;
    private final WebClient webClient;

    public Page<ClinicInvitationClinicDTO> getClinicInvitationsForDoctor(UUID doctorId, Pageable pageable) {
        Page<ClinicInvitation> clinicInvitations = clinicInvitationRepository.findAllByDoctor_Id(doctorId, pageable);
        return clinicInvitations.map(ClinicInvitationDTOMapper::toClinicDTO);
    }

    public Page<ClinicInvitationDoctorDTO> getClinicInvitationsForClinicSpecialty(UUID clinicSpecialtyId, Pageable pageable) {
        Page<ClinicInvitation> clinicInvitations = clinicInvitationRepository.findAllByClinicSpecialty_Id(clinicSpecialtyId, pageable);
        return clinicInvitations.map(ClinicInvitationDTOMapper::toDoctorDTO);
    }

    @Transactional
    public void createClinicInvitation(UUID clinicId, UUID specialtyId, UUID doctorId) {
        ClinicSpecialty clinicSpecialty = clinicSpecialtyRepository
                .findByClinic_IdAndSpecialty_Id(clinicId, specialtyId)
                .orElseThrow(() -> new RuntimeException("clinicSpecialty not found"));
        Boolean scheduleExists = webClient
                .get()
                .uri("http://localhost:4004/api/v3/clinic-specialty-schedule-template/check/{id}",
                        clinicSpecialty.getId())
                .header("X-Internal-Service", "clinic-service")
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (!Boolean.TRUE.equals(scheduleExists)) {
            throw new ClinicSpecialtyHasNoScheduleException("Chuyên khoa chưa đăng ký lịch làm");
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("doctor not found"));

        ClinicInvitation existing = clinicInvitationRepository
                .findAllByDoctor_IdAndClinicSpecialty_Id(doctorId, clinicSpecialty.getId());

        if (existing == null ||
                existing.getStatus() == InvitationStatus.REJECTED ||
                existing.getStatus() == InvitationStatus.CANCELLED) {
            saveNewInvitation(clinicSpecialty, doctor);
        } else if (existing.getStatus() == InvitationStatus.PENDING) {
            clinicInvitationRepository.deleteClinicInvitationById(existing.getId());
            saveNewInvitation(clinicSpecialty, doctor);
        } else if (existing.getStatus() == InvitationStatus.ACCEPTED) {
            throw new InvitationAlreadyAcceptedException("Bác sĩ đã trong chuyên khoa");
        }
    }

    private void saveNewInvitation(ClinicSpecialty clinicSpecialty, Doctor doctor) {
        ClinicInvitation newInvitation = new ClinicInvitation();
        newInvitation.setClinicSpecialty(clinicSpecialty);
        newInvitation.setDoctor(doctor);
        clinicInvitationRepository.save(newInvitation);
    }

    @Transactional
    public void approveClinicInvitation(UUID clinicInvitationId, InvitationStatus status) {

        ClinicInvitation clinicInvitation = clinicInvitationRepository.findById(clinicInvitationId)
                .orElseThrow(() -> new RuntimeException("clinicInvitation not found"));

        if (Objects.equals(status, InvitationStatus.ACCEPTED)) {
            handleAcceptedInvitation(clinicInvitation);
        }

        clinicInvitation.setStatus(status);
        clinicInvitation.setRespondedAt(LocalDateTime.now());

        clinicInvitationRepository.save(clinicInvitation);
    }

    @Transactional
    public void handleAcceptedInvitation(ClinicInvitation clinicInvitation) {
        Doctor doctor = doctorRepository.findById(clinicInvitation.getDoctor().getId())
                .orElseThrow(() -> new RuntimeException("doctor not found"));
        doctor.setInClinicSpecialty(true);
        doctorRepository.save(doctor);

        clinicSpecialtyDoctorService.createClinicSpecialtyDoctor(clinicInvitation);

        Map<String, String> requestBody = Map.of(
                "clinicSpecialtyId", clinicInvitation.getClinicSpecialty().getId().toString(),
                "doctorId", clinicInvitation.getDoctor().getId().toString()
        );

        Boolean succeed = WebClient.create()
                .post()
                .uri("http://localhost:4004/api/v3/clinic-specialty-schedule-template/sync-templates")
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(requestBody)
                .header("X-Internal-Service", "clinic-service")
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError,
                        clientResponse -> {
                            throw new RuntimeException("Failed to sync schedule templates");
                        })
                .bodyToMono(Boolean.class)
                .block();
        if (!Boolean.TRUE.equals(succeed)) {
            throw new RuntimeException("Failed to sync schedule templates");
        }
    }
}
