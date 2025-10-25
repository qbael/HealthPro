package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, UUID> {
    List<DoctorSpecialty> findAllByDoctor_Id(UUID doctorId);
    @Modifying
    @Query("DELETE FROM DoctorSpecialty ds WHERE ds.doctor.id = :doctorId")
    void deleteByDoctorId(@Param("doctorId") UUID doctorId);
}