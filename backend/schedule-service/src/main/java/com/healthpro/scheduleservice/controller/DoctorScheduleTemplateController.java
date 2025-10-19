package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.service.DoctorScheduleTemplateService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/schedules")
public class DoctorScheduleTemplateController {
    private final DoctorScheduleTemplateService doctorScheduleTemplateService;

    @GetMapping("/calendar/{userId}")
    public ResponseEntity<?> getDoctorCalendar(
            @PathVariable UUID userId,
            @RequestParam LocalDate weekStart
    ) {
        var calendar = doctorScheduleTemplateService.getWeeklyCalendar(userId, weekStart);
        return ResponseEntity.ok(calendar);
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createDoctorScheduleTemplate(
            @PathVariable UUID userId,
            @RequestBody DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
        doctorScheduleTemplateService.createDoctorScheduleTemplate(
                userId, doctorScheduleTemplateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateDoctorScheduleTemplate(
            @PathVariable UUID userId,
            @RequestBody DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
        doctorScheduleTemplateService.updateDoctorScheduleTemplate(
                userId, doctorScheduleTemplateRequestDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public void deleteDoctorScheduleTemplate(){
        doctorScheduleTemplateService.deleteAll();
    }
}
