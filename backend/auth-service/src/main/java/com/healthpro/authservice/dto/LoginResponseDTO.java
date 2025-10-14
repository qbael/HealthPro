package com.healthpro.authservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class LoginResponseDTO {
    private UUID id;
    private String email;
    private String role;

    public LoginResponseDTO(UUID id, String email, String role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
