package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {
    void deleteByDoctorIdAndAppointmentDateBetween(UUID doctorId, LocalDate startDate, LocalDate endDate);
    void deleteByAppointmentDateBefore(LocalDate today);
    List<DoctorAvailableSlot> findByDoctorIdAndAppointmentType(UUID doctorId, AppointmentType appointmentType);
}