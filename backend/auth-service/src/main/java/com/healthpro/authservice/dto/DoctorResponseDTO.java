package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorResponseDTO extends UserResponseDTO {
    private UUID doctorId;
    private String fullName;
    private String address;
}
