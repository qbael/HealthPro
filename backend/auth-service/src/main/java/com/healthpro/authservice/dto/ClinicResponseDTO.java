package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClinicResponseDTO extends UserResponseDTO {
    private UUID clinicId;
    private String clinicName;
    private String address;
    private String description;
    private String logoUrl;
    private String avatarUrl;
    private String weekdayOpenHour;
    private String weekdayCloseHour;
    private String weekendOpenHour;
    private String weekendCloseHour;
}
