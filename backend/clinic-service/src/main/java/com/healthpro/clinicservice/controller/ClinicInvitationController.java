package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ClinicInvitationRequestDTO;
import com.healthpro.clinicservice.service.ClinicInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clinic-invitation")
public class ClinicInvitationController {
    private final ClinicInvitationService  clinicInvitationService;

    @PostMapping
    public ResponseEntity<?> createClinicInvitation(
            @RequestHeader("X-UserRole-Id") UUID clinicId,
            @RequestBody ClinicInvitationRequestDTO clinicInvitationRequestDTO
    ) {
        clinicInvitationService
                .createClinicInvitation(clinicId, clinicInvitationRequestDTO.getSpecialtyId(), clinicInvitationRequestDTO.getDoctorId());
        return ResponseEntity.ok().build();
    }
}
