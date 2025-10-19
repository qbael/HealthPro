package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.UUID;

public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {
    void deleteByDoctorIdAndAppointmentDateBetween(UUID doctorId, LocalDate startDate, LocalDate endDate);
}