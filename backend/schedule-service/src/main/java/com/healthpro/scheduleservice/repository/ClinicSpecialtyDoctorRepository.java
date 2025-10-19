package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicSpecialtyDoctorRepository extends JpaRepository<ClinicSpecialtyDoctor, UUID> {
    List<ClinicSpecialtyDoctor> findByClinicSpecialtyId(UUID clinicSpecialtyId);
}