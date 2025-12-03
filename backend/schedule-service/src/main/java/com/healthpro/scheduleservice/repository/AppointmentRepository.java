package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, UUID> {
    List<Appointment> findAllByPatientId(UUID patientId);
    List<Appointment> findAllByDoctorId(UUID doctorId);
    List<Appointment> findAllByClinicId(UUID clinicId);
    Appointment findByPatientId(UUID patientId);
    Appointment findByDoctorId(UUID doctorId);
    Appointment findByClinicId(UUID clinicId);
}