package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.ClinicRequestDTO;
import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.service.ClinicService;
import com.healthpro.authservice.service.DoctorService;
import com.healthpro.authservice.service.PatientService;
import com.healthpro.authservice.utils.JwtUtil;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/profile")
public class ProfileController {
    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getProfile(
            @PathVariable UUID userId,
            @RequestParam String role
    ) {
        return switch (role) {
            case "PATIENT" -> ResponseEntity.ok().body(patientService.findByUserId(userId));
            case "DOCTOR" -> ResponseEntity.ok().body(doctorService.findByUserId(userId));
            case "CLINIC" -> ResponseEntity.ok().body(clinicService.findByUserId(userId));
            default -> ResponseEntity.badRequest().body("Không tồn tại role");
        };
    }

    @PutMapping("/patient/{userId}")
    public ResponseEntity<?> updatePatientProfile(
            @PathVariable UUID userId,
            @RequestBody PatientRequestDTO patientRequestDTO
    ) {
        patientService.updatePatient(userId, patientRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/doctor/{userId}")
    public ResponseEntity<?> updateDoctorProfile(
            @PathVariable UUID userId,
            @RequestBody DoctorRequestDTO doctorRequestDTO
    ) {
        doctorService.updateDoctor(userId, doctorRequestDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/clinic/{userId}")
    public ResponseEntity<?> updateClinicProfile(
            @PathVariable UUID userId,
            @RequestBody ClinicRequestDTO clinicRequestDTO
    ) {
        clinicService.updateClinic(userId, clinicRequestDTO);
        return ResponseEntity.ok().build();
    }
}
