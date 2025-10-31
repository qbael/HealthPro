package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.ApiResponseDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.DoctorResponseWebClientDTO;
import com.healthpro.authservice.service.DoctorService;
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
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    @GetMapping
    @Operation(summary = "Get doctors with pagination and sorting")
    public ResponseEntity<ApiResponseDTO<Page<DoctorResponseDTO>>> getDoctors(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Page<DoctorResponseDTO> doctors = doctorService.getDoctors(pageable);

        if (doctors.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(ApiResponseDTO.error(404, "Không tìm thấy bác sĩ nào"));
        }

        return ResponseEntity.ok(
                ApiResponseDTO.success(doctors, "Lấy danh sách bác sĩ thành công")
        );
    }

    @GetMapping("/{id}")
    public DoctorResponseWebClientDTO getClinicById(@PathVariable UUID id) {
        return doctorService.getDoctorById(id);
    }

//    @PostMapping
//    @Operation(summary = "Create a Doctor")
//    public ResponseEntity<DoctorResponseDTO> createDoctor(
//            @RequestBody DoctorRequestDTO doctorRequestDTO
//    ) {
//        DoctorResponseDTO doctorResponseDTO = doctorService.createDoctor(doctorRequestDTO);
//        return ResponseEntity.ok().body(doctorResponseDTO);
//    }

//    @PutMapping("/{id}")
//    @Operation(summary = "Update a Doctor")
//    public ResponseEntity<DoctorResponseDTO> updateDoctor(
//            @PathVariable UUID id,
//            @RequestBody DoctorRequestDTO doctorRequestDTO
//    ) {
//        DoctorResponseDTO doctorResponseDTO = doctorService.updateDoctor(id, doctorRequestDTO);
//        return ResponseEntity.ok().body(doctorResponseDTO);
//    }
}
