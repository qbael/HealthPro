package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ClinicRequestDTO {
    private UUID userId;
    private String clinicName;
    private String phoneNumber;
    private String address;
}
