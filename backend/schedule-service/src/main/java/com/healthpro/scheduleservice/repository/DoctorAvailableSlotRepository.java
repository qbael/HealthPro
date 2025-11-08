package com.healthpro.scheduleservice.repository;

import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface DoctorAvailableSlotRepository extends JpaRepository<DoctorAvailableSlot, UUID> {

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

    List<DoctorAvailableSlot> findByTemplateId(UUID templateId);

    @Query("SELECT MAX(d.appointmentDate) FROM DoctorAvailableSlot d")
    LocalDate findMaxAppointmentDate();

    void deleteByAppointmentDateLessThanEqual(LocalDate now);

    List<DoctorAvailableSlot> findByClinicSpecialtyIdAndAppointmentDateAndStartTimeAndEndTime(UUID clinicSpecialtyId, @NotNull(message = "Ngày hẹn không được để trống.") @FutureOrPresent(message = "Ngày hẹn phải là ngày hôm nay hoặc trong tương lai.") LocalDate appointmentDate, @NotNull(message = "Giờ bắt đầu không được để trống.") LocalTime startTime, @NotNull(message = "Giờ kết thúc không được để trống.") LocalTime endTime);

    void deleteAllByTemplateIdIn(Collection<UUID> templateIds);

    void deleteAllByTemplateId(UUID id);
}