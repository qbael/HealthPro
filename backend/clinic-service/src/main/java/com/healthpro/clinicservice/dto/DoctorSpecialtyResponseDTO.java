package com.healthpro.clinicservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorSpecialtyResponseDTO {
    private UUID id;
    private UUID doctorId;
    private UUID specialtyId;
    private String specialtyName;
}
