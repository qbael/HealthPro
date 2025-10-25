package com.healthpro.clinicservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClinicSpecialtyResponseDTO {
    private UUID id;
    private UUID clinicId;
    private UUID specialtyId;
    private String specialtyName;
}
