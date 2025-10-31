package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.service.DoctorScheduleTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v3/doctor-schedule-template")
public class DoctorScheduleTemplateController {
    private final DoctorScheduleTemplateService doctorScheduleTemplateService;

    @GetMapping
    public ResponseEntity<DoctorScheduleTemplateDTO> getAllDoctorScheduleTemplates(
            @RequestHeader("X-UserRole-Id") UUID userRoleId
    ) {
        DoctorScheduleTemplateDTO template = doctorScheduleTemplateService
                .getAllDoctorScheduleTemplates(userRoleId);
        return ResponseEntity.ok().body(template);
    }

    @PostMapping("/{doctorId}")
    public ResponseEntity<?> createDoctorScheduleTemplate(
            @PathVariable UUID doctorId,
            @RequestBody DoctorScheduleTemplateDTO doctorScheduleTemplateDTO
    ) {
        doctorScheduleTemplateService.createDoctorScheduleTemplate(
                doctorId, doctorScheduleTemplateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteDoctorScheduleTemplate(){
        doctorScheduleTemplateService.deleteAll();
    }
}
