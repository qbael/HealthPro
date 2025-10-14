package com.healthpro.authservice.controller;

import com.healthpro.authservice.service.ClinicService;
import com.healthpro.authservice.service.DoctorService;
import com.healthpro.authservice.service.PatientService;
import com.healthpro.authservice.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;
    private final JwtUtil jwtUtil;

    @GetMapping
    public ResponseEntity<?> getProfile(
            @CookieValue(value = "jwt", required = false) String token
    ) {

        System.out.println("token" + token);
        if (token == null || !jwtUtil.validateToken(token)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        UUID userId = jwtUtil.extractId(token);
        String role = jwtUtil.extractRole(token);

        return switch (role) {
            case "PATIENT" -> ResponseEntity.ok().body(patientService.findByUserId(userId));
            case "DOCTOR" -> ResponseEntity.ok().body(doctorService.findByUserId(userId));
            case "CLINIC" -> ResponseEntity.ok().body(clinicService.findByUserId(userId));
            default -> ResponseEntity.badRequest().body("Không tồn tại role");
        };
    }

//    @PostMapping("/profile")
//    public ResponseEntity<?> getProfile(
//            HttpServletRequest request
//    ) {
//        String token = jwtUtil.extractTokenFromCookie(request);
//        if (token == null || !jwtUtil.validateToken(token)) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
//        }
//
//        UUID userId = jwtUtil.extractId(token);
//        String role = jwtUtil.extractRole(token);
//
//        return switch (role) {
//            case "PATIENT" -> ResponseEntity.ok(patientService.createPatient(userId));
//            case "DOCTOR" -> ResponseEntity.ok(doctorService.createDoctor(userId));
//            case "CLINIC" -> ResponseEntity.ok(clinicService.createClinic(userId)));
//            default -> ResponseEntity.badRequest().body("Không tồn tại role");
//        };
//    }
}
