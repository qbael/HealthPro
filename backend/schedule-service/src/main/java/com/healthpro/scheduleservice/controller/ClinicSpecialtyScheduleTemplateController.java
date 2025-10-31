package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.service.ClinicSpecialtyScheduleTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v3/clinic-specialty-schedule-template")
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

    @GetMapping("/check/{clinicSpecialtyId}")
    public ResponseEntity<Boolean> checkClinicSpecialtyScheduleExists(
            @PathVariable UUID clinicSpecialtyId
            ) {
        boolean check = clinicSpecialtyScheduleTemplateService
                .checkClinicSpecialtyScheduleExists(clinicSpecialtyId);
        return ResponseEntity.ok(check);
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
