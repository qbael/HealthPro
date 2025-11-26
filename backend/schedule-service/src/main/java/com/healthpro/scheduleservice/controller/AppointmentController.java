package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ApiResponseDto;
import com.healthpro.scheduleservice.dto.AppointmentDataRequestDto;
import com.healthpro.scheduleservice.dto.AppointmentDataResponseDto;
import com.healthpro.scheduleservice.dto.AppointmentRequestDto;
import com.healthpro.scheduleservice.service.AppointmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v3/appointments")
public class AppointmentController {
    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @PostMapping("/appointment-data")
    public ResponseEntity<ApiResponseDto<AppointmentDataResponseDto>> getConfirmAppointmentData(@RequestBody @Valid AppointmentDataRequestDto dto) {
        AppointmentDataResponseDto responseDto = appointmentService.getConfirmAppointmentData(dto);
        return ResponseEntity.ok(ApiResponseDto.success(responseDto, "Lấy dữ liệu xác nhận cuộc hẹn thành công"));
    }

    @PostMapping
    public ResponseEntity<ApiResponseDto<Void>> createAppointment(@RequestBody @Valid AppointmentRequestDto dto) {
        boolean status = appointmentService.createAppointment(dto);
        if (status) {
            return ResponseEntity.ok(ApiResponseDto.success(null, "Tạo cuộc hẹn thành công"));
        } else {
            return ResponseEntity.status(400).body(ApiResponseDto.error(400, "Tạo cuộc hẹn thất bại"));
        }
    }
}
