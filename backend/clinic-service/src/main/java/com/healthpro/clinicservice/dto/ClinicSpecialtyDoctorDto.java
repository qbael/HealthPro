package com.healthpro.clinicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ClinicSpecialtyDoctorDto {
    private UUID id;
    private UUID clinicSpecialtyId;
    private UUID doctorId;
}
