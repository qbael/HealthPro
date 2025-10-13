package com.healthpro.authservice.dto;

import com.healthpro.authservice.entity.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class UserResponseDTO {
    private UUID id;
    private String email;
    private String phoneNumber;
    private String role;
    private Boolean isActive;
}
