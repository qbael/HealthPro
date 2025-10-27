package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.dto.DoctorSpecialtyRequestDTO;
import com.healthpro.clinicservice.dto.DoctorSpecialtyResponseDTO;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.service.DoctorSpecialtyService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/{specialtyId}")
    public ResponseEntity<ApiResponseDTO<Page<Doctor>>> getAllDoctorSpecialtiesBySpecialty(
            @PathVariable UUID specialtyId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Page<Doctor> doctors = doctorSpecialtyService
                .getAllDoctorSpecialtiesBySpecialty(specialtyId, pageable);

        if (doctors.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponseDTO.success(doctors, "Không có bác sĩ nào")
            );
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(doctors, "Lấy danh sách bác sĩ thành công")
        );
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
