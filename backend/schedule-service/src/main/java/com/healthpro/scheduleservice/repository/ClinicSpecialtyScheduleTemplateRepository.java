package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ClinicSpecialtyScheduleTemplateRepository extends JpaRepository<ClinicSpecialtyScheduleTemplate, UUID> {
    Page<ClinicSpecialtyScheduleTemplate> findByIsActiveTrue(Pageable pageable);
    void deleteByClinicSpecialtyId(UUID clinicSpecialtyId);
}