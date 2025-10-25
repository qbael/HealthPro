package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.DoctorAvailableSlotDTO;
import com.healthpro.scheduleservice.dto.ApiResponseDto;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/doctor-available-slots")
public class DoctorAvailableSlotController {
    private final DoctorAvailableSlotService doctorAvailableSlotService;

    @GetMapping
    public ResponseEntity<List<DoctorAvailableSlotDTO>> getAllSlot(
            @RequestHeader("X-UserRole-Id") UUID userRoleId,
            @RequestParam AppointmentType appointmentType
    ) {
        System.out.println(appointmentType);
        List<DoctorAvailableSlotDTO> slots = doctorAvailableSlotService.getAllSlot(userRoleId, appointmentType);

        return ResponseEntity.ok().body(slots);
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
