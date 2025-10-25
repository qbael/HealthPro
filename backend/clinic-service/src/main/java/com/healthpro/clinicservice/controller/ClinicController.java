package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
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

    @GetMapping("/{id}/specialties")
    @Operation(summary = "Get specialties by clinic id")
    public ResponseEntity<ApiResponseDto<List<ClinicSpecialty>>> getSpecialtiesByClinicId(@PathVariable String id) {
        Optional<List<ClinicSpecialty>> specialties = clinicService.getSpecialtiesByClinicId(id);
        return specialties.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách chuyên khoa của phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404, "Phòng khám không tồn tại hoặc không có chuyên khoa nào")));
    }

    @GetMapping("/current/specialties")  // ← /api/v1/clinics/current/specialties
    @Operation(summary = "Get specialties of CURRENT clinic")
    public ResponseEntity<ApiResponseDto<List<ClinicSpecialty>>> getCurrentClinicSpecialties(
            @RequestHeader("X-UserRole-Id") String clinicId  // ← GATEWAY TỰ INJECT!
    ) {
        Optional<List<ClinicSpecialty>> specialties = clinicService.getSpecialtiesByClinicId(clinicId);
        return specialties.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách chuyên khoa thành công")))
                .orElseGet(() -> ResponseEntity.ok().body(
                        ApiResponseDto.success(Collections.emptyList(), "Chưa có chuyên khoa nào")));
    }


}
