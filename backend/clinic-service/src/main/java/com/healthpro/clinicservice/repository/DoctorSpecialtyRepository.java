package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.DoctorSpecialty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorSpecialtyRepository extends JpaRepository<DoctorSpecialty, UUID> {
    List<DoctorSpecialty> findAllByDoctor_Id(UUID doctorId);
    List<DoctorSpecialty> findAllBySpecialty_Id(UUID doctorId);
//    Page<DoctorSpecialty> findAllBySpecialty_Id(UUID specialtyId, Pageable pageable);
    @Modifying
    @Query("DELETE FROM DoctorSpecialty ds WHERE ds.doctor.id = :doctorId")
    void deleteByDoctorId(@Param("doctorId") UUID doctorId);
}