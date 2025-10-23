package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ApiResponseDto;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@CrossOrigin("*")
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
    public ResponseEntity<List<DoctorAvailableSlot>> getAllSlot(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestParam AppointmentType appointmentType
    ) {
        System.out.println(appointmentType);
        Optional<List<DoctorAvailableSlot>> slots = doctorAvailableSlotService.getAllSlot(userRoleId, appointmentType);

        if (slots.isEmpty() || slots.get().isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(slots.get());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDto<List<DoctorAvailableSlot>>> getDoctorAvailableSlot(@PathVariable UUID id) {
        Optional<List<DoctorAvailableSlot>> slots = doctorAvailableSlotService.getDoctorAvailableSlots(id);
        System.out.println(slots.get().size());
        return slots.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách ngày khả dụng của bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404,  "Lấy danh sách ngày khả dụng của bác sĩ thất bại")));
    }

}
