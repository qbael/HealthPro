package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicSpecialtyRepository extends JpaRepository<ClinicSpecialty, UUID> {
    List<ClinicSpecialty> findAllByClinic_Id(UUID clinicId);
}