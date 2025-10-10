package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.LoginRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.service.AuthService;
import com.healthpro.authservice.service.RoleService;
import com.healthpro.authservice.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
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
            @RequestBody LoginRequestDTO loginRequestDTO,
            HttpServletResponse response
    ) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);

        String token = jwtUtil.generateToken(loginResponseDTO.getEmail(), loginResponseDTO.getRole());
        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60 * 3); // 3 day
        response.addCookie(cookie);

        return ResponseEntity.ok(loginResponseDTO);
    }
}
