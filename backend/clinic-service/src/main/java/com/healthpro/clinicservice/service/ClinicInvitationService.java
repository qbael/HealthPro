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
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDateTime;
import java.util.Objects;
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
        Boolean scheduleExists = webClient
                .get()
                .uri("http://localhost:8082/api/v1/clinic-specialty-schedule-template/check/{id}",
                        specialtyId)
                .retrieve()
                .bodyToMono(Boolean.class)
                .block();

        if (!Boolean.TRUE.equals(scheduleExists)) {
            throw new ClinicSpecialtyHasNoScheduleException("Chuyên khoa chưa đăng ký lịch làm");
        }

        ClinicSpecialty clinicSpecialty = clinicSpecialtyRepository
                .findByClinic_IdAndSpecialty_Id(clinicId, specialtyId)
                .orElseThrow(() -> new RuntimeException("clinicSpecialty not found") );
        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseThrow(() -> new RuntimeException("doctor not found") );

        ClinicInvitation existing = clinicInvitationRepository
                .findAllByDoctor_IdAndClinicSpecialty_Id(doctorId, clinicSpecialty.getId());

        if (existing == null ||
                existing.getStatus() == InvitationStatus.REJECTED ||
                existing.getStatus() == InvitationStatus.CANCELLED) {
            saveNewInvitation(clinicSpecialty, doctor);
        }

        else if (existing.getStatus() == InvitationStatus.PENDING) {
            clinicInvitationRepository.deleteClinicInvitationById(existing.getId());
            saveNewInvitation(clinicSpecialty, doctor);
        }

        else if (existing.getStatus() == InvitationStatus.ACCEPTED) {
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
    public void approveClinicInvitation(UUID clinicInvitationId, String status) {
        String cleanStatus = status.replace("\"", "").trim();

        ClinicInvitation clinicInvitation = clinicInvitationRepository.findById(clinicInvitationId)
                .orElseThrow(() -> new RuntimeException("clinicInvitation not found"));

        if (Objects.equals(cleanStatus, "ACCEPTED")) {
            handleAcceptedInvitation(clinicInvitation);
        }

        clinicInvitation.setStatus(InvitationStatus.valueOf(cleanStatus));
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
    }
}
