package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicRepository extends JpaRepository<Clinic, UUID> {

    @Query(value = """
        SELECT c.* FROM clinics c
        ORDER BY c.clinic_name
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Clinic> findAllClinics(
            @Param("limit") int limit,
            @Param("offset") int offset
    );

    @Query(value = """
        SELECT DISTINCT c.* FROM clinics c
        LEFT JOIN clinic_specialties cs ON cs.clinic_id = c.id
        LEFT JOIN specialties s ON s.id = cs.specialty_id
        WHERE 
          (
            :keyword = '' 
            OR LOWER(c.clinic_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.address) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(c.description) LIKE LOWER(CONCAT('%', :keyword, '%'))
          )
          AND (
            :specialtyList = '' 
            OR (
              s.name IS NOT NULL 
              AND LOWER(s.name) IN (
                SELECT LOWER(TRIM(val)) 
                FROM unnest(string_to_array(:specialtyList, ',')) AS val
              )
            )
          )
        ORDER BY c.clinic_name
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Clinic> searchClinicsByKeywordAndSpecialty(
            @Param("keyword") String keyword,
            @Param("specialtyList") String specialtyList,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}