package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.repository.DoctorRepository;
import com.healthpro.authservice.service.DoctorService;
import com.healthpro.authservice.utils.JwtUtil;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {
    private final DoctorService doctorService;
    private final JwtUtil jwtUtil;

    public DoctorController(DoctorService doctorService, JwtUtil jwtUtil) {
        this.doctorService = doctorService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping
    @Operation(summary = "Get Doctors")
    public ResponseEntity<List<DoctorResponseDTO>> getDoctors() {
        List<DoctorResponseDTO> doctors = doctorService.getDoctors();
        return ResponseEntity.ok().body(doctors);
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
