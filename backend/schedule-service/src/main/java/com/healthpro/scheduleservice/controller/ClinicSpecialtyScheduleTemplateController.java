package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.service.ClinicSpecialtyScheduleTemplateService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v3/clinic-specialty-schedule-template")
public class ClinicSpecialtyScheduleTemplateController {
    private final ClinicSpecialtyScheduleTemplateService clinicSpecialtyScheduleTemplateService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ClinicSpecialtyScheduleTemplateDTO>> getAllClinicSpecialtyScheduleTemplates(
            @PathVariable UUID id
    ) {
        List<ClinicSpecialtyScheduleTemplateDTO> templates = clinicSpecialtyScheduleTemplateService
                .getAllClinicSpecialtyScheduleTemplates(id);
        return ResponseEntity.ok(templates);
    }

    @GetMapping("/check/{clinicSpecialtyId}")
    public ResponseEntity<Boolean> checkClinicSpecialtyScheduleExists(
            @PathVariable UUID clinicSpecialtyId
            ) {
        boolean check = clinicSpecialtyScheduleTemplateService
                .checkClinicSpecialtyScheduleExists(clinicSpecialtyId);
        return ResponseEntity.ok(check);
    }

    public record CreateClinicSpecialtyScheduleTemplateRequest(
            @Valid List<ClinicSpecialtyScheduleTemplateDTO> templates
    ) {}

    @PostMapping("/{id}")
    public ResponseEntity<?> createClinicSpecialtyScheduleTemplate(
            @PathVariable UUID id,
            @RequestBody @Valid CreateClinicSpecialtyScheduleTemplateRequest request
    ) {
        clinicSpecialtyScheduleTemplateService.createClinicSpecialtyScheduleTemplate(
                id, request.templates);
        return ResponseEntity.noContent().build();
    }
}
