package com.healthpro.scheduleservice.entity;

import com.healthpro.scheduleservice.entity.enums.AppointmentStatus;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "appointments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Appointment {

    @Id
    @GeneratedValue(strategy =  GenerationType.UUID)
    private UUID id;

    @NotNull(message = "ID bệnh nhân không được để trống")
    @Column(name = "patient_id", nullable = false)
    private UUID patientId;

    @NotBlank(message = "Tên bệnh nhân không được để trống")
    @Size(max = 255, message = "Tên bệnh nhân không được vượt quá 255 ký tự")
    @Column(name = "patient_name", nullable = false)
    private String patientName;

    @Email(message = "Email bệnh nhân không hợp lệ")
    @Column(name = "patient_email")
    private String patientEmail;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại bệnh nhân không hợp lệ")
    @Column(name = "patient_phone")
    private String patientPhone;

    @NotNull(message = "ID bác sĩ không được để trống")
    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @NotBlank(message = "Tên bác sĩ không được để trống")
    @Size(max = 255, message = "Tên bác sĩ không được vượt quá 255 ký tự")
    @Column(name = "doctor_name", nullable = false)
    private String doctorName;

    @Column(name = "clinic_id")
    private UUID clinicId;

    @Size(max = 255, message = "Tên phòng khám không được vượt quá 255 ký tự")
    @Column(name = "clinic_name")
    private String clinicName;

    @NotNull(message = "Địa chỉ nơi khám không được để trống")
    @Column(name = "address", nullable = false)
    private String address;

    @NotNull(message = "Số điện thoại phòng khám không được để trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại phòng khám không hợp lệ")
    @Column(name = "clinic_phone", nullable = false)
    private String  clinic_phone;

    @Column(name = "clinic_specialty_id")
    private UUID clinicSpecialtyId;

    @Column(name = "specialty_name")
    private String specialtyName;

    @NotNull(message = "Loại lịch hẹn không được để trống")
    @Enumerated(EnumType.STRING)
    @Column(name = "appointment_type", nullable = false)
    private AppointmentType appointmentType;

    @Future(message = "Ngày hẹn phải là hiện tại hoặc tương lai")
    @NotNull(message = "Ngày hẹn không được để trống")
    @Column(name = "appointment_date", nullable = false)
    private LocalDate appointmentDate;

    @NotNull(message = "Thời gian bắt đầu không được để trống")
    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @NotNull(message = "Thời gian kết thúc không được để trống")
    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @NotNull(message = "Trạng thái không được để trống")
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private AppointmentStatus status;

    @Size(max = 1000, message = "Ghi chú không được vượt quá 1000 ký tự")
    @Column(name = "notes", columnDefinition = "TEXT")
    private String notes;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    @AssertTrue(message = "Thời gian kết thúc phải sau thời gian bắt đầu")
    public boolean isEndTimeAfterStartTime() {
        if (startTime == null || endTime == null) {
            return true;
        }
        return endTime.isAfter(startTime);
    }
}