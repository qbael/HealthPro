package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.LoginRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RoleService roleService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
    }

    public LoginResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userService.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("A user with this email " + "already exists"
                    + signupRequestDTO.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPassword(hashedPassword);
        Role role = roleService.findByRole(signupRequestDTO.getRole());
        user.setRole(role);
        userService.create(user);

        User createdUser = userService.findByEmail(signupRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email " + signupRequestDTO.getEmail() + " not found"));

        return new LoginResponseDTO(createdUser.getId(), createdUser.getEmail(), user.getRole());
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userService.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email " + loginRequestDTO.getEmail() + " not found"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        return new LoginResponseDTO(user.getId(), user.getEmail(), user.getRole());
    }

    public LoginResponseDTO getCurrentUser( String token) {
        UUID id = jwtUtil.extractId(token);
        String email = jwtUtil.extractEmail(token);
        Role role = jwtUtil.extractRole(token);

        return new LoginResponseDTO(id, email, role);
    }
}
