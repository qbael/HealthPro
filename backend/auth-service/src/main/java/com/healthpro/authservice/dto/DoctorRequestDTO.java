package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class DoctorRequestDTO {
    private UUID userId;
    private String fullName;
    private String phoneNumber;
    private String address;
}
