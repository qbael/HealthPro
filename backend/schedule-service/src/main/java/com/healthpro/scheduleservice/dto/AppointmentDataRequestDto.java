package com.healthpro.scheduleservice.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.Value;

import java.util.UUID;

@Data
public class AppointmentDataRequestDto {
    @NotNull(message = "ID bệnh nhân không được để trống")
    UUID userId;
    UUID doctorId;
    UUID clinicSpecialtyId;
}