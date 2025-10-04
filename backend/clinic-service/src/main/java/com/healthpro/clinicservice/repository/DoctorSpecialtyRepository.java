package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, UUID> {
}