package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.DoctorSpecialtyRequestDTO;
import com.healthpro.clinicservice.dto.DoctorSpecialtyResponseDTO;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.entity.DoctorSpecialty;
import com.healthpro.clinicservice.service.DoctorSpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/doctor-specialty")
public class DoctorSpecialtyController {
    private DoctorSpecialtyService doctorSpecialtyService;

    @GetMapping
    public ResponseEntity<List<DoctorSpecialtyResponseDTO>> getAllDoctorSpecialties(
            @RequestHeader("X-UserRole-Id") UUID userRoleId
    ) {
        List<DoctorSpecialtyResponseDTO> template = doctorSpecialtyService
                .getAllDoctorSpecialties(userRoleId);
        return ResponseEntity.ok(template);
    }

    @PostMapping()
    public ResponseEntity<?> createDoctorSpecialty(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestBody DoctorSpecialtyRequestDTO doctorSpecialtyRequestDTO
    ) {
        doctorSpecialtyService.createDoctorSpecialty(
                userRoleId, doctorSpecialtyRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
