package com.healthpro.clinicservice.repository;

import com.healthpro.clinicservice.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, UUID> {

    Page<Doctor> findDistinctByDoctorSpecialties_Specialty_IdAndIsInClinicSpecialtyFalse(UUID specialtyId, Pageable pageable);

    @Query("""
            SELECT DISTINCT d
            FROM Doctor d
            JOIN d.doctorSpecialties ds
            WHERE ds.specialty.id = :specialtyId
              AND d.isInClinicSpecialty = false
              AND NOT EXISTS (
                  SELECT 1
                  FROM ClinicInvitation ci
                  WHERE ci.doctor.id = d.id
                    AND ci.clinicSpecialty.specialty.id = :specialtyId
              )
            """)
    Page<Doctor> findEligibleDoctorsBySpecialtyWithoutInvitations(
            @Param("specialtyId") UUID specialtyId,
            Pageable pageable
    );

    /**
     * Tìm kiếm bác sĩ theo keyword, specialty list và specialtyId
     * - keyword: tìm theo tên, bio
     * - specialtyList: danh sách chuyên khoa từ mapping bệnh (VD: "Thần kinh,Da liễu")
     * - specialtyId: lọc thêm theo 1 chuyên khoa cụ thể (optional)
     */
    @Query(value = """
        SELECT DISTINCT d.* FROM doctors d
        LEFT JOIN doctor_specialties ds ON ds.doctor_id = d.id
        LEFT JOIN specialties s ON s.id = ds.specialty_id
        WHERE 
          (
            :keyword = '' 
            OR LOWER(d.full_name) LIKE LOWER(CONCAT('%', :keyword, '%'))
            OR LOWER(d.bio) LIKE LOWER(CONCAT('%', :keyword, '%'))
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
          AND (
            :specialtyId = '' 
            OR :specialtyId IS NULL
            OR s.id = CAST(:specialtyId AS uuid)
          )
        ORDER BY d.full_name
        LIMIT :limit OFFSET :offset
        """, nativeQuery = true)
    List<Doctor> searchDoctorsByKeywordAndSpecialty(
            @Param("keyword") String keyword,
            @Param("specialtyList") String specialtyList,
            @Param("specialtyId") String specialtyId,
            @Param("limit") int limit,
            @Param("offset") int offset
    );
}