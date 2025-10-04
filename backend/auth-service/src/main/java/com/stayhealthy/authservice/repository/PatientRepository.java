package com.stayhealthy.authservice.repository;

import com.stayhealthy.authservice.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PatientRepository extends JpaRepository<Patient, UUID> {
}