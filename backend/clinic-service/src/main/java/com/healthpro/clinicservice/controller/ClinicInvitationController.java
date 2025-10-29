package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationDoctorDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationRequestDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationClinicDTO;
import com.healthpro.clinicservice.entity.enums.InvitationStatus;
import com.healthpro.clinicservice.service.ClinicInvitationService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clinic-invitation")
public class ClinicInvitationController {
    private final ClinicInvitationService  clinicInvitationService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<ClinicInvitationClinicDTO>>>
        getClinicInvitationsForDoctor(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));

        Page<ClinicInvitationClinicDTO> clinicInvitations = clinicInvitationService
                .getClinicInvitationsForDoctor(userRoleId, pageable);

        if (clinicInvitations.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponseDTO.success(clinicInvitations, "Không có lời mời nào")
            );
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(clinicInvitations, "Lấy danh sách lời mời thành công")
        );
    }

    @GetMapping("/{clinicSpecialtyId}")
    public ResponseEntity<ApiResponseDTO<Page<ClinicInvitationDoctorDTO>>>
        getClinicInvitationsForClinicSpecialty(
            @PathVariable UUID clinicSpecialtyId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));

        Page<ClinicInvitationDoctorDTO> clinicInvitations = clinicInvitationService
                .getClinicInvitationsForClinicSpecialty(clinicSpecialtyId, pageable);

        if (clinicInvitations.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponseDTO.success(clinicInvitations, "Không có lời mời nào")
            );
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(clinicInvitations, "Lấy danh sách lời mời thành công")
        );
    }

    @PostMapping
    public ResponseEntity<?> createClinicInvitation(
            @RequestHeader("X-UserRole-Id") UUID clinicId,
            @RequestBody ClinicInvitationRequestDTO clinicInvitationRequestDTO
    ) {
        clinicInvitationService
                .createClinicInvitation
                        (clinicId,
                         clinicInvitationRequestDTO.getSpecialtyId(),
                         clinicInvitationRequestDTO.getDoctorId());
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{invitationId}")
    public ResponseEntity<?> approveClinicInvitation(
            @PathVariable UUID invitationId,
            @RequestBody String status
    ) {
        clinicInvitationService
                .approveClinicInvitation(invitationId, status);
        return ResponseEntity.ok().build();
    }
}
