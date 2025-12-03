package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.ApiResponseDTO;
import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.dto.ClinicResponseWebClientDTO;
import com.healthpro.authservice.dto.ClinicTimeDTO;
import com.healthpro.authservice.service.ClinicService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {
    private final ClinicService clinicService;

    @GetMapping
    @Operation(summary = "Get doctors with pagination and sorting")
    public ResponseEntity<ApiResponseDTO<Page<ClinicResponseDTO>>> getClinics(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Page<ClinicResponseDTO> clinics = clinicService.getClinics(pageable);

        if (clinics.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDTO.error(404, "Không tìm thấy phòng khám nào"));
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(clinics, "Lấy danh sách phòng khám thành công")
        );
    }

    @GetMapping("/{id}")
    public ClinicResponseWebClientDTO getClinicById(@PathVariable UUID id) {
        return clinicService.getClinicById(id);
    }

    @GetMapping("/time/{id}")
    public ResponseEntity<ClinicTimeDTO> getTimeClinic(@PathVariable UUID id) {
        ClinicTimeDTO clinicTimeDTO = clinicService.getTimeClinic(id);

        return ResponseEntity.ok(clinicTimeDTO);
    }
}
