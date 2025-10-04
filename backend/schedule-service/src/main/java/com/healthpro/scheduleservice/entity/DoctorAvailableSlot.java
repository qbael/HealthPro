package com.healthpro.scheduleservice.entity;

import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "doctor_available_slots")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DoctorAvailableSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "ID của bác sĩ không được để trống.")
    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @NotNull(message = "ID của chuyên khoa tại phòng khám không được để trống.")
    @Column(name = "clinic_specialty_id", nullable = false)
    private UUID clinicSpecialtyId;

    @NotNull(message = "Ngày hẹn không được để trống.")
    @FutureOrPresent(message = "Ngày hẹn phải là ngày hôm nay hoặc trong tương lai.")
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @NotNull(message = "Giờ bắt đầu không được để trống.")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "Giờ kết thúc không được để trống.")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type")
    private AppointmentType appointmentType;
}