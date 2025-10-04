package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicSpecialtyDoctor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicSpecialtyDoctorRepository extends JpaRepository<ClinicSpecialtyDoctor, UUID> {
}