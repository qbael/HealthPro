package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorResponseDTO extends UserResponseDTO {
    private String fullName;
    private String address;
}
