package com.healthpro.authservice.dto;

import com.healthpro.authservice.entity.Role;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
public class LoginResponseDTO {
    private UUID id;
    private String email;
    private Role role;

    public LoginResponseDTO(UUID id, String email, Role role) {
        this.id = id;
        this.email = email;
        this.role = role;
    }
}
