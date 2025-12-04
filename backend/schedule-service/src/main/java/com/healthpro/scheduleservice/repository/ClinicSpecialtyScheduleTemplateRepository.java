package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Repository
public interface ClinicSpecialtyScheduleTemplateRepository extends JpaRepository<ClinicSpecialtyScheduleTemplate, UUID> {

    List<ClinicSpecialtyScheduleTemplate> findAllByClinicSpecialtyId(UUID clinicSpecialtyId);

    boolean existsByClinicSpecialtyId(UUID clinicSpecialtyId);

    List<ClinicSpecialtyScheduleTemplate> findAllByDayOfWeek(DayOfWeek dayOfWeek);

    ClinicSpecialtyScheduleTemplate findByClinicSpecialtyIdAndDayOfWeek(UUID clinicSpecialtyId, DayOfWeek dayOfWeek);
}