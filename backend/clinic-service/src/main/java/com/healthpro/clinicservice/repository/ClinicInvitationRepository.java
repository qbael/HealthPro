package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicInvitation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClinicInvitationRepository extends JpaRepository<ClinicInvitation, UUID> {
}