package com.healthpro.clinicservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClinicInvitationRequestDTO {
    private UUID specialtyId;
    private UUID doctorId;
}
