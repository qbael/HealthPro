package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.entity.Specialty;
import com.healthpro.clinicservice.service.SpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/specialties")
public class SpecialtyController {
    private final SpecialtyService specialtyService;

    @GetMapping
    @Operation(summary = "Get all available specialties")
    public ResponseEntity<ApiResponseDTO<List<Specialty>>> getAllSpecialties() {
        List<Specialty> specialties = specialtyService.getAllSpecialties();

        return ResponseEntity.ok().body(
                ApiResponseDTO.success(specialties, "Lấy danh sách chuyên khoa chung thành công"));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get all available specialties")
    public ResponseEntity<Specialty> getSpecialtyById(
            @PathVariable UUID id
    ) {
        Specialty specialty = specialtyService.getSpecialtyById((id));
        return ResponseEntity.ok().body(specialty);
    }
}
