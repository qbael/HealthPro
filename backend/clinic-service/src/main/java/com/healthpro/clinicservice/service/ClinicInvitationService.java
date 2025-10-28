package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicInvitationClinicDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationDoctorDTO;
import com.healthpro.clinicservice.entity.ClinicInvitation;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.entity.enums.InvitationStatus;
import com.healthpro.clinicservice.mapper.ClinicInvitationDTOMapper;
import com.healthpro.clinicservice.repository.ClinicInvitationRepository;
import com.healthpro.clinicservice.repository.ClinicSpecialtyRepository;
import com.healthpro.clinicservice.repository.DoctorRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClinicInvitationService {
    private final ClinicInvitationRepository clinicInvitationRepository;
    private final ClinicSpecialtyRepository clinicSpecialtyRepository;
    private final ClinicSpecialtyDoctorService clinicSpecialtyDoctorService;
    private final DoctorRepository doctorRepository;

    public Page<ClinicInvitationDoctorDTO> getClinicInvitationsByDoctor(UUID doctorId, Pageable pageable) {
        Page<ClinicInvitation> clinicInvitations = clinicInvitationRepository.findAllByDoctor_Id(doctorId, pageable);
        return clinicInvitations.map(ClinicInvitationDTOMapper::toDoctorDTO);
    }

    public Page<ClinicInvitationClinicDTO> getClinicInvitationsByClinicSpecialty(UUID clinicSpecialtyId, Pageable pageable) {
        Page<ClinicInvitation> clinicInvitations = clinicInvitationRepository.findAllByClinicSpecialty_Id(clinicSpecialtyId, pageable);
        return clinicInvitations.map(ClinicInvitationDTOMapper::toClinicDTO);
    }

    public void createClinicInvitation(UUID clinicId, UUID specialtyId, UUID doctorId) {
        ClinicSpecialty clinicSpecialty = clinicSpecialtyRepository
                .findByClinic_IdAndSpecialty_Id(clinicId, specialtyId)
                .orElseThrow(() -> new RuntimeException("clinicSpecialty not found") );
        Doctor doctor = doctorRepository.findById(doctorId)
            .orElseThrow(() -> new RuntimeException("doctor not found") );

        ClinicInvitation clinicInvitation = new ClinicInvitation();
        clinicInvitation.setClinicSpecialty(clinicSpecialty);
        clinicInvitation.setDoctor(doctor);
        clinicInvitationRepository.save(clinicInvitation);
    }

    @Transactional
    public void approveClinicInvitation(UUID clinicInvitationId, InvitationStatus status) {
        ClinicInvitation clinicInvitation = clinicInvitationRepository.findById(clinicInvitationId)
                .orElseThrow(() -> new RuntimeException("clinicInvitation not found"));

        if (status == InvitationStatus.ACCEPTED)
            handleAcceptedInvitation(clinicInvitation);

        clinicInvitation.setStatus(status);
        clinicInvitationRepository.save(clinicInvitation);
    }

    private void handleAcceptedInvitation(ClinicInvitation clinicInvitation) {
        clinicSpecialtyDoctorService.createClinicSpecialtyDoctor(clinicInvitation);
    }
}
