package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicInvitation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicInvitationRepository extends JpaRepository<ClinicInvitation, UUID> {
}