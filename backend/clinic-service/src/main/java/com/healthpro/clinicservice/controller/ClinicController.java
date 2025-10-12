package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.service.ClinicService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/clinics")
public class ClinicController {
    public final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    @GetMapping
    public ResponseEntity<ApiResponseDto<List<Clinic>>> getClinics(
            @RequestParam (required = false, defaultValue = "9") String maxResults
    ) {
        return clinicService.getClinics(Integer.parseInt(maxResults))
                .map(clinics -> ResponseEntity.ok().body(
                        ApiResponseDto.success(clinics, "Lấy danh sách phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(ApiResponseDto.error(404, "Lấy danh sách phòng khám thất bại")));
    }
}
