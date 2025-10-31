package com.healthpro.clinicservice.dto;

import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ClinicSpecialtyInfoResponseDto(
        @NotNull(message = "ID chuyên khoa không được để trống") UUID clinicSpecialtyId,
        String specialtyName,
        UUID clinicId
) {}
