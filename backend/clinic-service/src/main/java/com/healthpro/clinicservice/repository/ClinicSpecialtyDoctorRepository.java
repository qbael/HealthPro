package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicSpecialtyDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClinicSpecialtyDoctorRepository extends JpaRepository<ClinicSpecialtyDoctor, UUID> {
}