package com.healthpro.clinicservice.dto;

import com.healthpro.clinicservice.entity.Specialty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClinicSpecialtyResponseDTO {
    private UUID clinicId;
    private Specialty specialty;
}
