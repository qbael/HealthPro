package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface DoctorScheduleTemplateRepository extends JpaRepository<DoctorScheduleTemplate, UUID> {
}