package com.healthpro.scheduleservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.sql.Time;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(name = "doctor_schedule_templates")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DoctorScheduleTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "ID của bác sĩ không được để trống.")
    @Column(name = "doctor_id", nullable = false)
    private UUID doctorId;

    @NotNull(message = "Ngày trong tuần không được để trống.")
    @Enumerated(EnumType.STRING)
    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Thời gian bắt đầu không được để trống.")
    @Column(name = "from_time", nullable = false)
    private LocalTime fromTime;

    @NotNull(message = "Thời gian kết thúc không được để trống.")
    @Column(name = "to_time", nullable = false)
    private LocalTime toTime;

    @NotNull(message = "Thời lượng mỗi ca khám không được để trống.")
    @Min(value = 1, message = "Thời lượng mỗi ca khám phải là một số dương (tính bằng phút).")
    @Column(name = "slot_duration", nullable = false)
    private Integer slotDuration;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updatedAt;
}
