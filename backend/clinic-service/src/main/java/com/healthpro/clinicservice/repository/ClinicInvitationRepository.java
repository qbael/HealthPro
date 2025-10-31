package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicInvitation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClinicInvitationRepository extends JpaRepository<ClinicInvitation, UUID> {
    Page<ClinicInvitation> findAllByDoctor_Id(UUID doctorId, Pageable pageable);

    Page<ClinicInvitation> findAllByClinicSpecialty_Id(UUID clinicSpecialtyId, Pageable pageable);

    ClinicInvitation findAllByDoctor_IdAndClinicSpecialty_Id(UUID doctorId, UUID clinicSpecialtyId);

    void deleteClinicInvitationById(UUID id);
}