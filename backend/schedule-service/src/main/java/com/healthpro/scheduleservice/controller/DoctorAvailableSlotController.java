package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import com.healthpro.scheduleservice.service.ScheduleGenerationService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/doctor-available-slots")
public class DoctorAvailableSlotController {
    private final DoctorAvailableSlotService doctorAvailableSlotService;

    public DoctorAvailableSlotController(DoctorAvailableSlotService doctorAvailableSlotService) {
        this.doctorAvailableSlotService = doctorAvailableSlotService;
    }

    @GetMapping
    public List<DoctorAvailableSlot> getAllSlot() {
        return doctorAvailableSlotService.getAllSlot();
    }
}
