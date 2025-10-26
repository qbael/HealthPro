package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {
    @Query("""
        SELECT DISTINCT d
        FROM Doctor d
        JOIN d.doctorSpecialties ds
        WHERE ds.specialty.id = :specialtyId
    """)
    Page<Doctor> findAllBySpecialtyId(@Param("specialtyId") UUID specialtyId, Pageable pageable);
}