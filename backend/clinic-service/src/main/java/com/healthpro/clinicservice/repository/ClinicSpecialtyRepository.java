package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.ClinicSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClinicSpecialtyRepository extends JpaRepository<ClinicSpecialty, UUID> {
    List<ClinicSpecialty> findAllByClinic_Id(UUID clinicId);
    @Modifying
    @Query("DELETE FROM ClinicSpecialty ds WHERE ds.clinic.id = :clinicId")
    void deleteByClinicId(@Param("clinicId") UUID clinicId);
    Optional<ClinicSpecialty> findByClinic_IdAndSpecialty_Id(UUID clinicId, UUID specialtyId);
}