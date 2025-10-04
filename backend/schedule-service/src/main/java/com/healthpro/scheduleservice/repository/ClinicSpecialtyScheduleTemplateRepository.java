package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ClinicSpecialtyScheduleTemplateRepository extends JpaRepository<ClinicSpecialtyScheduleTemplate, UUID> {
}