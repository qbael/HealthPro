package com.healthpro.authservice.dto;

import jakarta.validation.constraints.*;

import java.util.UUID;

public record AppointmentInfoResponseDto(
        @NotNull(message = "ID bệnh nhân không được để trống") UUID patientId,
        @Size(message = "Tên bệnh nhân không được vượt quá 255 ký tự", max = 255) @NotBlank(message = "Tên bệnh nhân không được để trống") String patientName,
        @Email(message = "Email bệnh nhân không hợp lệ") String patientEmail,
        @Pattern(message = "Số điện thoại bệnh nhân không hợp lệ", regexp = "^0[0-9]{9}$") String patientPhone,
        UUID doctorId,
        @Size(message = "Tên bác sĩ không được vượt quá 255 ký tự", max = 255) @NotBlank(message = "Tên bác sĩ không được để trống") String doctorName,
        UUID clinicId,
        @Size(message = "Tên phòng khám không được vượt quá 255 ký tự", max = 255) String clinicName,
        @NotNull(message = "Địa chỉ nơi khám không được để trống") String address,
        @NotNull(message = "Số điện thoại phòng khám không được để trống") @Pattern(message = "Số điện thoại phòng khám không hợp lệ", regexp = "^0[0-9]{9}$") String phone
) {}
