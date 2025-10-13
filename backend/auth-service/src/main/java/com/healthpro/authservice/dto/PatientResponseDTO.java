package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class PatientResponseDTO extends UserResponseDTO {
    private UUID patientId;
    private String fullName;
}
