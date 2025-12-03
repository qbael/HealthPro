package com.healthpro.authservice.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
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
public class ClinicDto {
    @NotNull(message = "Id phòng khám không được trống")
    private UUID id;

    @NotNull(message = "Id người dùng không được trống")
    private UUID userId;

    @Size(max = 255, message = "Tên phòng khám phải dưới 255 kí tự")
    private String clinicName;

    @Size(max = 255, message = "Địa chỉ phải dưới 255 kí tự")
    private String address;

    private String description;

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ mở cửa phải theo định dạng HH:mm")
    private String weekdayOpenHour;

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ đóng cửa phải theo định dạng HH:mm")
    private String weekdayCloseHour;

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ mở cửa phải theo định dạng HH:mm")
    private String weekendOpenHour;

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ đóng cửa phải theo định dạng HH:mm")
    private String weekendCloseHour;

    @URL(message = "URL không hợp lệ")
    private String logoUrl;

    @URL(message = "URL không hợp lệ")
    private String avatarUrl;
}
