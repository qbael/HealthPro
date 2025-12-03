package com.healthpro.authservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorDto {
    @NotNull(message = "Id bác sĩ không được để trống")
    private UUID id;

    @NotNull(message = "Id người dùng không được để trống ")
    private UUID userId;

    @Size(min = 2, max = 255, message = "Họ tên phải dưới 255 kí tự")
    private String fullName;

    private String bio;

    private Boolean gender;

    @Size(max = 255, message = "Họ tên phải dưới 255 kí tự")
    private String address;

    @URL(message = "URL không hợp lệ")
    @Size(max = 255)
    private String avatarUrl;
}
