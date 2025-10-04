package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Specialty;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface SpecialtyRepository extends JpaRepository<Specialty, UUID> {
}