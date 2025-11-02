package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api/v2/clinics")
public class ClinicController {
    public final ClinicService clinicService;

    @GetMapping
    public ResponseEntity<ApiResponseDTO<Page<Clinic>>> getClinics(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Page<Clinic> clinics = clinicService.getClinics(pageable);

        if (clinics.isEmpty()) {
            return ResponseEntity.ok(
                    ApiResponseDTO.success(clinics, "Không có phòng khám nào")
            );
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(clinics, "Lấy danh sách phòng khám thành công")
        );
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get clinic by id")
    public ResponseEntity<ApiResponseDTO<Clinic>> getDoctorById(@PathVariable String id) {
        Optional<Clinic> clinic = clinicService.getClinicById(id);
        return clinic.map(value -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(value, "Lấy thông tin phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDTO.error(404, "Phòng khám không tồn tại")));
    }

    @GetMapping("/{id}/specialties")
    @Operation(summary = "Get specialties by clinic id")
    public ResponseEntity<ApiResponseDTO<List<ClinicSpecialty>>> getSpecialtiesByClinicId(@PathVariable String id) {
        Optional<List<ClinicSpecialty>> specialties = clinicService.getSpecialtiesByClinicId(id);
        return specialties.map(value -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(value, "Lấy danh sách chuyên khoa của phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDTO.error(404, "Phòng khám không tồn tại hoặc không có chuyên khoa nào")));
    }

    @GetMapping("/current/specialties")  // ← /api/v1/clinics/current/specialties
    @Operation(summary = "Get specialties of CURRENT clinic")
    public ResponseEntity<ApiResponseDTO<List<ClinicSpecialty>>> getCurrentClinicSpecialties(
            @RequestHeader("X-UserRole-Id") String clinicId  // ← GATEWAY TỰ INJECT!
    ) {
        Optional<List<ClinicSpecialty>> specialties = clinicService.getSpecialtiesByClinicId(clinicId);
        return specialties.map(value -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(value, "Lấy danh sách chuyên khoa thành công")))
                .orElseGet(() -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(Collections.emptyList(), "Chưa có chuyên khoa nào")));
    }


}
