package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.AppointmentInfoResponseDto;
import com.healthpro.authservice.service.AppointmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/appointment-info")
    public AppointmentInfoResponseDto getAppointmentInfo(@RequestParam(name = "patientId") String patientId,
                                                         @RequestParam(name = "doctorId", required = false) String doctorId,
                                                         @RequestParam(name = "clinicId", required = false) String clinicId) {
        return appointmentService.getAppointmentInfo(patientId, doctorId, clinicId);
    }
}
