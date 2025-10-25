package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ClinicSpecialtyRequestDTO;
import com.healthpro.clinicservice.dto.ClinicSpecialtyResponseDTO;
import com.healthpro.clinicservice.service.ClinicSpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clinic-specialty")
public class ClinicSpecialtyController {
    private ClinicSpecialtyService clinicSpecialtyService;

    @GetMapping
    public ResponseEntity<List<ClinicSpecialtyResponseDTO>> getAllClinicSpecialties(
            @RequestHeader("X-UserRole-Id") UUID userRoleId
    ) {
        List<ClinicSpecialtyResponseDTO> template = clinicSpecialtyService
                .getAllClinicSpecialties(userRoleId);
        return ResponseEntity.ok(template);
    }

    @PostMapping()
    public ResponseEntity<?> createClinicSpecialty(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestBody ClinicSpecialtyRequestDTO clinicSpecialtyRequestDTO
            ) {
        clinicSpecialtyService.createClinicSpecialty(
                userRoleId, clinicSpecialtyRequestDTO);
        return ResponseEntity.noContent().build();
    }
}
