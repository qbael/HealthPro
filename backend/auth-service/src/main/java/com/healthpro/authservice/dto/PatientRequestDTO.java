package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PatientRequestDTO {
    private UUID userId;
    private String fullName;
    private String phoneNumber;
}
