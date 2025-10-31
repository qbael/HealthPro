package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDTO;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.entity.DoctorSpecialty;
import com.healthpro.clinicservice.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v2/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @Operation(summary = "Get doctors with pagination and sorting")
    public ResponseEntity<ApiResponseDTO<Page<Doctor>>> getDoctors(
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "12") Integer limit,
            @RequestParam(required = false, defaultValue = "id") String sortBy,
            @RequestParam(required = false, defaultValue = "asc") String sortDir
    ) {
        Pageable pageable = PageRequest.of(page, limit, Sort.by(
                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
        ));
        Optional<Page<Doctor>> doctors = doctorService.getDoctors(pageable);
        return doctors.map(doctorList -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(doctorList, "Lấy danh sách bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDTO.error(404, "Lấy danh sách bác sĩ thất bại")));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get doctor by id")
    public ResponseEntity<ApiResponseDTO<Doctor>> getDoctorById(@PathVariable String id) {
        Optional<Doctor> doctor = doctorService.getDoctorById(id);
        return doctor.map(value -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(value, "Lấy thông tin bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDTO.error(404, "Bác sĩ không tồn tại")));
    }

    @GetMapping("/{id}/specialties")
    @Operation(summary = "Get specialties by doctor id")
    public ResponseEntity<ApiResponseDTO<List<DoctorSpecialty>>> getSpecialtiesByClinicId(@PathVariable String id) {
        Optional<List<DoctorSpecialty>> specialties = doctorService.getSpecialtiesByDoctorId(id);
        return specialties.map(value -> ResponseEntity.ok().body(
                        ApiResponseDTO.success(value, "Lấy danh sách chuyên khoa của bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDTO.error(404, "Lấy danh sách chuyên khoa của bác sĩ thất bại")));
    }
}
