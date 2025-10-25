package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.service.ClinicSpecialtyScheduleTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/clinic-specialty-schedule-template")
public class ClinicSpecialtyScheduleTemplateController {
    private final ClinicSpecialtyScheduleTemplateService clinicSpecialtyScheduleTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<ClinicSpecialtyScheduleTemplateDTO> getAllClinicSpecialtyScheduleTemplates(
            @PathVariable UUID id
    ) {
        ClinicSpecialtyScheduleTemplateDTO template = clinicSpecialtyScheduleTemplateService
                .getAllClinicSpecialtyScheduleTemplates(id);
        return ResponseEntity.ok(template);
    }

    @PostMapping("/{id}")
    public ResponseEntity<?> createClinicSpecialtyScheduleTemplate(
            @PathVariable UUID id,
            @RequestBody ClinicSpecialtyScheduleTemplateDTO clinicSpecialtyScheduleTemplateDTO
    ) {
        clinicSpecialtyScheduleTemplateService.createClinicSpecialtyScheduleTemplate(
                id, clinicSpecialtyScheduleTemplateDTO);
        return ResponseEntity.noContent().build();
    }
}
