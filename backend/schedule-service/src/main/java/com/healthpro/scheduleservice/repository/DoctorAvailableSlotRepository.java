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
import java.util.UUID;

@Repository
public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {
    void deleteByDoctorIdAndAppointmentDateBetween(UUID doctorId, LocalDate startDate, LocalDate endDate);
    void deleteByAppointmentDateBefore(LocalDate today);
    List<DoctorAvailableSlot> findByDoctorIdAndAppointmentType(UUID doctorId, AppointmentType appointmentType);

    @Query("""
        SELECT DISTINCT d.appointmentDate
        FROM DoctorAvailableSlot d
        WHERE d.doctorId = :doctorId AND d.appointmentType = 'DOCTOR'
        ORDER BY d.appointmentDate
        """)
    List<LocalDate> findAllDoctorAvailableDates(@Param("doctorId") UUID doctorId);

    List<AvailableTimeSlot> findAllByDoctorIdAndAppointmentTypeAndAppointmentDate(UUID doctorId, AppointmentType appointmentType, LocalDate appointmentDate);

    @Query("""
        SELECT DISTINCT d.appointmentDate
        FROM DoctorAvailableSlot d
        WHERE d.clinicSpecialtyId = :clinicSpecialtyId AND d.appointmentType = 'CLINIC'
        ORDER BY d.appointmentDate
        """)
    List<LocalDate> findAllClinicSpecialtyAvailableDates(UUID clinicSpecialtyId);

    @Query("""
        SELECT NEW com.healthpro.scheduleservice.dto.AvailableTimeSlot(d.appointmentDate, d.startTime, d.endTime)
        FROM DoctorAvailableSlot d
        WHERE d.clinicSpecialtyId = :clinicSpecialtyId AND d.appointmentType = :appointmentType AND d.appointmentDate = :appointmentDate
        ORDER BY d.startTime
        """)
    LinkedHashSet<AvailableTimeSlot> findAllByClinicSpecialtyIdAndAppointmentTypeAndAppointmentDate(UUID clinicSpecialtyId, AppointmentType appointmentType, LocalDate appointmentDate);
}