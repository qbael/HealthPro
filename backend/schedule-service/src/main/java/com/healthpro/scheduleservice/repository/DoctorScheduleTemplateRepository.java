package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorScheduleTemplateRepository extends JpaRepository<DoctorScheduleTemplate, UUID> {
    Optional<List<DoctorScheduleTemplate>> findByDoctorIdAndIsActiveTrue(UUID id);

    Page<DoctorScheduleTemplate> findByIsActiveTrue(Pageable pageable);
}