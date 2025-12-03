package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClinicSpecialtyDoctorRepository extends JpaRepository<ClinicSpecialtyDoctor, UUID> {
    List<ClinicSpecialtyDoctor> findByClinicSpecialtyId(UUID clinicSpecialtyId);

    Optional<ClinicSpecialtyDoctor> findByDoctorId(UUID doctorId);

    Optional<ClinicSpecialtyDoctor> findByClinicSpecialtyIdAndDoctorId(UUID clinicSpecialtyId, UUID doctorId);

    void deleteByClinicSpecialtyIdAndDoctorId(UUID clinicSpecialtyId, UUID doctorId);
}