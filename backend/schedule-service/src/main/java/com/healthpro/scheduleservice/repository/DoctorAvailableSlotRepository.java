package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.print.Doc;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {
    void deleteByDoctorIdAndAppointmentDateBetween(UUID doctorId, LocalDate startDate, LocalDate endDate);
    void deleteByAppointmentDateBefore(LocalDate today);
    List<DoctorAvailableSlot> findByDoctorIdAndAppointmentType(UUID doctorId, AppointmentType appointmentType);

    @Query("""
        SELECT DISTINCT d.appointmentDate
        FROM DoctorAvailableSlot d
        WHERE d.doctorId = :doctorId
        ORDER BY d.appointmentDate
        """)
    List<LocalDate> findAllDoctorAvailableDates(@Param("doctorId") UUID doctorId);

    List<DoctorAvailableSlot> findAllByDoctorId(UUID doctorId);
}