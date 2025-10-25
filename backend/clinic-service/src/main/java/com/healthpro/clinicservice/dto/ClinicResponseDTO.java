package com.healthpro.clinicservice.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.UUID;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class ClinicResponseDTO {
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
