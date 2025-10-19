package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.LoginRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.service.AuthService;
import com.healthpro.authservice.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    private final AuthService authService;
    private final JwtUtil jwtUtil;

    public AuthController(AuthService authService, JwtUtil jwtUtil) {
        this.authService = authService;
        this.jwtUtil = jwtUtil;
    }

    private ResponseEntity<LoginResponseDTO> getLoginResponseDTOResponseEntity(
            HttpServletResponse response, LoginResponseDTO loginResponseDTO
    ) {
        String token = jwtUtil.generateToken(loginResponseDTO.getEmail(), loginResponseDTO.getId(),
                loginResponseDTO.getUserRoleId(), loginResponseDTO.getRole());

        Cookie cookie = new Cookie("jwt", token);
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(24 * 60 * 60 * 3);
        cookie.setSecure(true);
        response.addCookie(cookie);

        response.addHeader("Set-Cookie", String.format(
                "jwt=%s; Max-Age=%d; Path=/; HttpOnly; SameSite=None",
                token,
                24 * 60 * 60 * 3
        ));

        return ResponseEntity.ok(loginResponseDTO);
    }

    @PostMapping("/signup")
    @Operation(summary = "Signup User")
    public ResponseEntity<LoginResponseDTO> signup(
            @RequestBody SignupRequestDTO signupRequestDTO,
            HttpServletResponse response
    ) {
        LoginResponseDTO signupResponseDTO = authService.signup(signupRequestDTO);

        return getLoginResponseDTOResponseEntity(response, signupResponseDTO);
    }

    @PostMapping("/login")
    @Operation(summary = "Login User")
    public ResponseEntity<LoginResponseDTO> login(
            @RequestBody LoginRequestDTO loginRequestDTO,
            HttpServletResponse response
    ) {
        LoginResponseDTO loginResponseDTO = authService.login(loginRequestDTO);

        return getLoginResponseDTOResponseEntity(response, loginResponseDTO);
    }

    @PostMapping("/logout")
    @Operation(summary = "Logout User")
    public ResponseEntity<Void> logout (HttpServletResponse response){
        Cookie cookie = new Cookie("jwt", "");
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        cookie.setMaxAge(0);
        response.addCookie(cookie);

        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Get Current Logged In User")
    @GetMapping("/current")
    public ResponseEntity<LoginResponseDTO> getCurrentUser(
            @CookieValue(value = "jwt", required = false) String token) {
        if (token == null || token.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        if (!jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        return ResponseEntity.ok(authService.getCurrentUser(token));
    }

    @Operation(summary = "Validate Token")
    @GetMapping("/validate")
    public ResponseEntity<Void> validateToken(
            @CookieValue(value = "jwt", required = false) String jwtCookie) {
        if (jwtCookie == null || jwtCookie.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        try {
            jwtUtil.validateToken(jwtCookie);

            return ResponseEntity.ok().build();
        } catch (JwtException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
