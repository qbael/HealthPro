package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicRepository extends JpaRepository<Clinic, UUID> {
}