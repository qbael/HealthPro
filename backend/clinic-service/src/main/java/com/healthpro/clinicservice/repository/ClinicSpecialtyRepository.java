package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicSpecialtyRepository extends JpaRepository<ClinicSpecialty, UUID> {
}