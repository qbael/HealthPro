package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ApiResponseDto;
import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule/doctor")
@CrossOrigin(origins = "*")
public class DoctorAvailableSlotController {
    private final DoctorAvailableSlotService doctorAvailableSlotService;

    @GetMapping("/available-dates/{doctorId}")
    public ResponseEntity<ApiResponseDto<List<LocalDate>>> getDoctorAvailableDates(@PathVariable UUID doctorId) {
        Optional<List<LocalDate>> dates = doctorAvailableSlotService.getAvailableDatesByDoctorId(doctorId);
        return dates.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách ngày khả dụng của bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404,  "Lấy danh sách ngày khả dụng của bác sĩ thất bại")));
    }

    @GetMapping("/available-slots/{doctorId}")
    public ResponseEntity<ApiResponseDto<List<AvailableTimeSlot>>> getDoctorTypeAvailableSlotsByDoctorId(@PathVariable UUID doctorId,
                                                                                                        @RequestParam(required = true) LocalDate date) {
        Optional<List<AvailableTimeSlot>> slots = doctorAvailableSlotService.getDoctorTypeAvailableSlotsByDoctorId(doctorId, date);
        return slots.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách giờ hẹn của bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404,  "Lấy danh sách giờ  của bác sĩ thất bại")));
    }

    @GetMapping("/fast-available-dates/{doctorId}")
    public ResponseEntity<ApiResponseDto<Map<LocalDate, Long>>> getFastAvailableDatesByDoctorId(@PathVariable UUID doctorId) {
        Optional<Map<LocalDate, Long>> dates = doctorAvailableSlotService.getFastAvailableDatesByDoctorId(doctorId);
        return dates.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách ngày hẹn nhanh của bác sĩ thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(ApiResponseDto.error(404,  "Lấy danh sách ngày hẹn nhanh của bác sĩ thất bại")));
    }

}
