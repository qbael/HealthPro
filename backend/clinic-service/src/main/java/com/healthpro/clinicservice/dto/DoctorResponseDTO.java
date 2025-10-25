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
public class DoctorResponseDTO {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String bio;
    private Boolean gender;
    private String address;
    private String avatarUrl;
}
