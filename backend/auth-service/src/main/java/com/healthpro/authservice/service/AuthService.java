package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.LoginRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.utils.JwtUtil;
import jakarta.transaction.Transactional;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    public AuthService(UserService userService, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, RoleService roleService, PatientService patientService, DoctorService doctorService, ClinicService clinicService) {
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.roleService = roleService;
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    @Transactional
    public LoginResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userService.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("A user with this email " + "already exists"
                    + signupRequestDTO.getEmail());
        }

        String hashedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhoneNumber(signupRequestDTO.getPhoneNumber());
        user.setPassword(hashedPassword);
        Role role = roleService.findByRole(signupRequestDTO.getRole());
        user.setRole(role);
        User createdUser = userService.create(user);
        System.out.println(createdUser.getId());

        switch (signupRequestDTO.getRole()) {
            case "PATIENT": patientService.createPatient(createdUser);
                break;
            case "DOCTOR": doctorService.createDoctor(createdUser);
                break;
            case "CLINIC": clinicService.createClinic(createdUser);
                break;
            default: throw new BadCredentialsException("Invalid role");
        }

        return new LoginResponseDTO(createdUser.getId(), createdUser.getEmail(), signupRequestDTO.getRole());
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userService.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "User with email " + loginRequestDTO.getEmail() + " not found"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Sai mật khẩu");
        }

        return new LoginResponseDTO(user.getId(), user.getEmail(), user.getRole().getRoleName());
    }

    public LoginResponseDTO getCurrentUser( String token) {
        UUID id = jwtUtil.extractId(token);
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        return new LoginResponseDTO(id, email, role);
    }
}
