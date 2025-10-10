package com.healthpro.authservice.dto;

import com.healthpro.authservice.entity.Role;

import java.util.UUID;

public class LoginResponseDTO {
    private final String token;
    private UUID id;
    private String email;
    private Role role;
}
