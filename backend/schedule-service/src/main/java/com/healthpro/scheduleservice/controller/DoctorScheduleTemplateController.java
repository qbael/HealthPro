package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.service.DoctorScheduleTemplateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v3/doctor-schedule-template")
public class DoctorScheduleTemplateController {
    private final DoctorScheduleTemplateService doctorScheduleTemplateService;

    @GetMapping
    public ResponseEntity<List<DoctorScheduleTemplateDTO>> getAllDoctorScheduleTemplates(
            @RequestHeader("X-UserRole-Id") UUID userRoleId
    ) {
        List<DoctorScheduleTemplateDTO> templates =
                doctorScheduleTemplateService.getAllDoctorScheduleTemplates(userRoleId);
        return ResponseEntity.ok().body(templates);
    }

    public record CreateDoctorScheduleTemplateRequest(
           @Valid List<DoctorScheduleTemplateDTO> templates
    ) {}

    @PostMapping("/{doctorId}")
    public ResponseEntity<?> createDoctorScheduleTemplate(
            @PathVariable("doctorId") @NotNull UUID doctorId,
            @RequestBody @Valid CreateDoctorScheduleTemplateRequest request
    ) {
        doctorScheduleTemplateService.createDoctorScheduleTemplate(
                doctorId, request.templates);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteDoctorScheduleTemplate(){
        doctorScheduleTemplateService.deleteAll();
    }
}
