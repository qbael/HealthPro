package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.ApiResponseDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/patients")
public class PatientController {
    private final PatientService patientService;

    @GetMapping
    @Operation(summary = "Get doctors with pagination and sorting")
    public ResponseEntity<ApiResponseDTO<Page<PatientResponseDTO>>> getDoctors(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Page<PatientResponseDTO> patients = patientService.getPatients(pageable);

        if (patients.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDTO.error(404, "Không tìm thấy bệnh nhân nào"));
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(patients, "Lấy danh sách bệnh nhân thành công")
        );
    }
}
