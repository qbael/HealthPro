package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Repository
public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {
    void deleteByDoctorIdAndAppointmentDateBetween(UUID doctorId, LocalDate startDate, LocalDate endDate);
    void deleteByAppointmentDateBefore(LocalDate today);

    @Query("""
        SELECT DISTINCT d.appointmentDate
        FROM DoctorAvailableSlot d
        WHERE d.doctorId = :doctorId AND d.appointmentType = 'DOCTOR'
        ORDER BY d.appointmentDate
        """)
    List<LocalDate> findAllDoctorAvailableDates(UUID doctorId);

    List<AvailableTimeSlot> findAllByDoctorIdAndAppointmentTypeAndAppointmentDate(UUID doctorId, AppointmentType appointmentType, LocalDate appointmentDate);

    @Query("""
        SELECT DISTINCT d.appointmentDate
        FROM DoctorAvailableSlot d
        WHERE d.clinicSpecialtyId = :clinicSpecialtyId AND d.appointmentType = 'CLINIC'
        ORDER BY d.appointmentDate
        """)
    List<LocalDate> findAllClinicSpecialtyAvailableDates(UUID clinicSpecialtyId);

    List<AvailableTimeSlot> findAllByClinicSpecialtyIdAndAppointmentTypeAndAppointmentDate(UUID clinicSpecialtyId, AppointmentType appointmentType, LocalDate appointmentDate);

    @Query("""
        SELECT d.appointmentDate AS date, COUNT(d) AS count
        FROM DoctorAvailableSlot d
        WHERE d.doctorId = :doctorId AND d.appointmentType = 'DOCTOR'
        GROUP BY d.appointmentDate
        ORDER BY d.appointmentDate
    """)
    List<Object[]> findFastAvailableDatesByDoctorId(@Param("doctorId") UUID doctorId);
}