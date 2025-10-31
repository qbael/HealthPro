package com.healthpro.scheduleservice.dto;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotNull;
import lombok.Value;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

@Value
public class AvailableTimeSlot {
    UUID id;
    @NotNull(message = "Ngày hẹn không được để trống.")
    @FutureOrPresent(message = "Ngày hẹn phải là ngày hôm nay hoặc trong tương lai.")
    LocalDate appointmentDate;
    @NotNull(message = "Giờ bắt đầu không được để trống.")
    LocalTime startTime;
    @NotNull(message = "Giờ kết thúc không được để trống.")
    LocalTime endTime;
}
