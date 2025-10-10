package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.service.AuthService;
import com.healthpro.authservice.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final RoleService roleService;

    public AuthController(AuthService authService, RoleService roleService) {
        this.authService = authService;
        this.roleService = roleService;
    }

    @PostMapping("/signup")
    @Operation(summary = "Signup User")
    public ResponseEntity<?> signup(
            @RequestBody SignupRequestDTO signupRequestDTO
    ) {
        authService.signup(signupRequestDTO);
        return ResponseEntity.ok(Map.of("message", "Signup successful"));
    }

    @PostMapping("/login")
    @Operation(summary = "Login User")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody SignupRequestDTO signupRequestDTO
    ) {
        Optional<String> tokenOptional = authService.login(signupRequestDTO);

        if(tokenOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String token = tokenOptional.get();
        Role role = roleService.findByRole(signupRequestDTO.getRole());
        return ResponseEntity.ok(new LoginResponseDTO(token, signupRequestDTO.getEmail(), role));
    }
}
