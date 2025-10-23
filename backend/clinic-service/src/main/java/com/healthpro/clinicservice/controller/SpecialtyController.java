package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Specialty;
import com.healthpro.clinicservice.service.SpecialtyService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/specialties")
public class SpecialtyController {

    private final SpecialtyService specialtyService;

    public SpecialtyController(SpecialtyService specialtyService) {
        this.specialtyService = specialtyService;
    }

    @GetMapping
    @Operation(summary = "Get all available specialties")
    public ResponseEntity<ApiResponseDto<List<Specialty>>> getAllSpecialties() {
        List<Specialty> specialties = specialtyService.getAllSpecialties();

        return ResponseEntity.ok().body(
                ApiResponseDto.success(specialties, "Lấy danh sách chuyên khoa chung thành công"));
    }
}
