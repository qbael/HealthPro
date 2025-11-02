package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    Page<Doctor> findDistinctByDoctorSpecialties_Specialty_IdAndIsInClinicSpecialtyFalse(UUID specialtyId, Pageable pageable);

}