package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.service.DoctorService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/doctors")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    @Operation(summary = "Get Doctors with specialties")
    public ResponseEntity<ApiResponseDto<List<Doctor>>> getDoctors(
            @RequestParam(required = false, defaultValue = "12") String maxResults
    ) {
        Optional<List<Doctor>> doctors = doctorService.getDoctors(Integer.parseInt(maxResults));
        return doctors.map(doctorList -> ResponseEntity.ok().body(
                        ApiResponseDto.success(doctorList, "Lấy danh sách bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404, "Lấy danh sách bác sĩ thất bại")));
    }
}
