package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorAvailableSlotDTO;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("api/v1/doctor-available-slots")
public class DoctorAvailableSlotController {
    private final DoctorAvailableSlotService doctorAvailableSlotService;

    public DoctorAvailableSlotController(DoctorAvailableSlotService doctorAvailableSlotService) {
        this.doctorAvailableSlotService = doctorAvailableSlotService;
    }

//    @GetMapping
//    public Optional<List<DoctorAvailableSlot>> getAllSlot(
//            @RequestHeader("X-UserRole-Id") UUID userRoleId,
//            @RequestParam AppointmentType appointmentType
//    ) {
//        return doctorAvailableSlotService.getAllSlot(userRoleId, appointmentType);
//    }

    @GetMapping
    public ResponseEntity<List<DoctorAvailableSlotDTO>> getAllSlot(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestParam AppointmentType appointmentType
    ) {
        System.out.println(appointmentType);
        List<DoctorAvailableSlotDTO> slots = doctorAvailableSlotService.getAllSlot(userRoleId, appointmentType);

        return ResponseEntity.ok().body(slots);
    }

}
