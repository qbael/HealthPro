package com.healthpro.clinicservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorSpecialtyRequestDTO {
    private UUID doctorId;
    private UUID[] specialty;
}
