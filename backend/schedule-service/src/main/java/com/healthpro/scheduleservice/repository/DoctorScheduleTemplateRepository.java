package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.DayOfWeek;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorScheduleTemplateRepository extends JpaRepository<DoctorScheduleTemplate, UUID> {
    List<DoctorScheduleTemplate> findByDoctorId(UUID id);

    List<DoctorScheduleTemplate> findByDayOfWeek(DayOfWeek dayOfWeek);

    List<DoctorScheduleTemplate> findByDoctorIdIn(List<UUID> list);

    List<DoctorScheduleTemplate> findByDoctorIdInAndDayOfWeekIn(List<UUID> doctorIds, List<DayOfWeek> daysOfWeek);

    List<DoctorScheduleTemplate> findByDoctorIdAndDayOfWeekIn(UUID doctorId, List<DayOfWeek> daysOfWeek1);
}