package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {
    public final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<Page<Clinic>>> getClinics(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        return clinicService.getClinics(pageable)
                .map(clinics -> ResponseEntity.ok().body(
                        ApiResponseDto.success(clinics, "Lấy danh sách phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(ApiResponseDto.error(404, "Lấy danh sách phòng khám thất bại")));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clinic by id")
    public ResponseEntity<ApiResponseDto<Clinic>> getDoctorById(@PathVariable String id) {
        Optional<Clinic> clinic = clinicService.getClinicById(id);
        return clinic.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy thông tin phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404, "Phòng khám không tồn tại")));
    }
}
