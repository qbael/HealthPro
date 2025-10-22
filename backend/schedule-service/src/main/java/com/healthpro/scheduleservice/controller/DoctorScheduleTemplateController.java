package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateResponseDTO;
import com.healthpro.scheduleservice.service.DoctorScheduleTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule-template")
public class DoctorScheduleTemplateController {
    private final DoctorScheduleTemplateService doctorScheduleTemplateService;

    @GetMapping
    public ResponseEntity<DoctorScheduleTemplateResponseDTO> getAllDoctorScheduleTemplates(
            @RequestHeader("X-UserRole-Id") UUID userRoleId
    ) {
        DoctorScheduleTemplateResponseDTO template = doctorScheduleTemplateService
                .getAllDoctorScheduleTemplates(userRoleId);
        return ResponseEntity.ok().body(template);
    }

    @PostMapping()
    public ResponseEntity<?> createDoctorScheduleTemplate(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestBody DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
        doctorScheduleTemplateService.createDoctorScheduleTemplate(
                userRoleId, doctorScheduleTemplateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteDoctorScheduleTemplate(){
        doctorScheduleTemplateService.deleteAll();
    }
}
