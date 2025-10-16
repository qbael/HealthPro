package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
public class PatientRequestDTO extends UserResponseDTO {
    private UUID patientId;
    private String fullName;
    private String phoneNumber;
    private LocalDate dateOfBirth;
    private Boolean gender;
    private String medicalNotes;
}
