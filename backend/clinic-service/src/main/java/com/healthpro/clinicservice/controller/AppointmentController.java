package com.healthpro.clinicservice.controller;

import com.healthpro.clinicservice.dto.ClinicSpecialtyInfoResponseDto;
import com.healthpro.clinicservice.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/clinic-specialty-info")
    public ClinicSpecialtyInfoResponseDto getClinicSpecialtyInfo(@RequestParam(name = "clinicSpecialtyId") String specialtyId) {
        return appointmentService.getClinicSpecialtyInfo(specialtyId);
    }
}
