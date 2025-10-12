package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.UserRequestDTO;
import com.healthpro.authservice.dto.UserResponseDTO;
import com.healthpro.authservice.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @Operation(summary = "Get Doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctors() {
        List<DoctorResponseDTO> doctors = doctorService.getDoctors();
        return ResponseEntity.ok().body(doctors);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Doctor")
    public ResponseEntity<DoctorResponseDTO> updateDoctor(
            @PathVariable UUID id,
            @RequestBody DoctorRequestDTO doctorRequestDTO
    ) {
        DoctorResponseDTO doctorResponseDTO = doctorService.updateDoctor(id, doctorRequestDTO);
        return ResponseEntity.ok().body(doctorResponseDTO);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a Doctor")
    public ResponseEntity<Void> deleteDoctor(
            @PathVariable UUID id
    ) {
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
