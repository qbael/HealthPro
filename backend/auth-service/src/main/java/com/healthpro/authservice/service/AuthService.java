package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.utils.JwtUtil;
import io.jsonwebtoken.JwtException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

    public void signup(SignupRequestDTO signupRequestDTO) {
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
    }

    public Optional<String> login(SignupRequestDTO signupRequestDTO) {
        Optional<String> token = userService
                .findByEmail(signupRequestDTO.getEmail())
                .filter(u -> passwordEncoder.matches(signupRequestDTO.getPassword(),
                        u.getPassword()))
                .map(u->jwtUtil.generateToken(u.getEmail(), u.getRole()));

        return token;
    }

    public boolean validateToken(String token) {
        try {
            jwtUtil.validateToken(token);
            return true;
        }
        catch (JwtException e) {
            return false;
        }
    }
}
