package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.LoginRequestDTO;
import com.healthpro.authservice.dto.LoginResponseDTO;
import com.healthpro.authservice.dto.SignupRequestDTO;
import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.exception.WrongPassWordException;
import com.healthpro.authservice.repository.PatientRepository;
import com.healthpro.authservice.utils.JwtUtil;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class AuthService {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final RoleService roleService;
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    @Transactional
    public LoginResponseDTO signup(SignupRequestDTO signupRequestDTO) {
        if (userService.findByEmail(signupRequestDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Email này đã tồn tại");
        }

        String hashedPassword = passwordEncoder.encode(signupRequestDTO.getPassword());
        User user = new User();
        user.setEmail(signupRequestDTO.getEmail());
        user.setPhoneNumber(signupRequestDTO.getPhoneNumber());
        user.setPassword(hashedPassword);
        Role role = roleService.findByRole(signupRequestDTO.getRole());
        user.setRole(role);
        User createdUser = userService.create(user);

        UUID userRoleId = switch (signupRequestDTO.getRole()) {
            case "PATIENT" -> patientService.createPatient(createdUser);
            case "DOCTOR" -> doctorService.createDoctor(createdUser);
            case "CLINIC" -> clinicService.createClinic(createdUser);
            default -> throw new BadCredentialsException("Invalid role");
        };

        return new LoginResponseDTO(createdUser.getId(), userRoleId, createdUser.getEmail(), signupRequestDTO.getRole());
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO) {
        User user = userService.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new UserNotFoundException(
                        "Không tìm thấy email"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), user.getPassword())) {
            throw new WrongPassWordException("Sai mật khẩu");
        }

        UUID userRoleId = switch (user.getRole().getRoleName()) {
            case "PATIENT" -> patientService.findByUserId(user.getId()).getPatientId();
            case "DOCTOR" -> doctorService.findByUserId(user.getId()).getDoctorId();
            case "CLINIC" -> clinicService.findByUserId(user.getId()).getClinicId();
            default -> throw new BadCredentialsException("Invalid role");
        };

        return new LoginResponseDTO(user.getId(), userRoleId, user.getEmail(), user.getRole().getRoleName());
    }

    public LoginResponseDTO getCurrentUser( String token) {
        UUID id = jwtUtil.extractId(token);
        UUID userRoleId = jwtUtil.extractUserRoleId(token);
        String email = jwtUtil.extractEmail(token);
        String role = jwtUtil.extractRole(token);

        return new LoginResponseDTO(id, userRoleId, email, role);
    }
}
