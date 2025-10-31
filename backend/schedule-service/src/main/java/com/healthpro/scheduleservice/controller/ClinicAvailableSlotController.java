package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ApiResponseDto;
import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.service.ClinicAvailableSlotService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/schedule/clinic-specialty")
@CrossOrigin(origins = "*")
public class ClinicAvailableSlotController {
    private final ClinicAvailableSlotService clinicAvailableSlotService;

    @GetMapping("/available-dates/{clinicSpecialtyId}")
    public ResponseEntity<ApiResponseDto<List<LocalDate>>> getClinicSpecialtyAvailableDates(@PathVariable UUID clinicSpecialtyId) {
        Optional<List<LocalDate>> dates = clinicAvailableSlotService.getClinicSpecialtyAvailableDates(clinicSpecialtyId);
        return dates.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách ngày khả dụng của chuyên khoa tại phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(ApiResponseDto.error(404, "Lấy danh sách ngày khả dụng của chuyên khoa tại phòng khám thất bại")));
    }

    @GetMapping("/available-slots/{clinicSpecialtyId}")
    public ResponseEntity<ApiResponseDto<List<AvailableTimeSlot>>> getClinicTypeAvailableSlotsByClinicSpecialtyId(@PathVariable UUID clinicSpecialtyId,
                                                                                                                  @RequestParam(required = true) LocalDate date) {
        Optional<List<AvailableTimeSlot>> slots = clinicAvailableSlotService.getClinicTypeAvailableSlotsByClinicSpecialtyId(clinicSpecialtyId, date);
        return slots.map(value -> ResponseEntity.ok().body(
                        ApiResponseDto.success(value, "Lấy danh sách giờ hẹn của chuyên khoa tại phòng khám thành công")))
                .orElseGet(() -> ResponseEntity
                        .status(404)
                        .body(ApiResponseDto.error(404, "Lấy danh sách giờ hẹn của chuyên khoa tại phòng khám thất bại")));
    }
}
