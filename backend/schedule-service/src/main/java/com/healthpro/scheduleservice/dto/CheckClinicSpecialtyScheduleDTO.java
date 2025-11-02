package com.healthpro.scheduleservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class CheckClinicSpecialtyScheduleDTO {
    private UUID clinicId;
    private UUID specialtyId;
}
