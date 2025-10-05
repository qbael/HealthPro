package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
}