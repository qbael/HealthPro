package com.healthpro.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicResponseWebClientDTO {
    private UUID id;
    private UUID userId;
    private String clinicName;
    private String address;
    private String description;
    private String weekdayOpenHour;
    private String weekdayCloseHour;
    private String weekendOpenHour;
    private String weekendCloseHour;
    private String logoUrl;
    private String avatarUrl;
}
