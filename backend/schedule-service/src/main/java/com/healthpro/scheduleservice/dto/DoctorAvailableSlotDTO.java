package com.healthpro.scheduleservice.dto;

import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
public class DoctorAvailableSlotDTO {
    private LocalDate appointmentDate;
    private List<SlotInfo> slots;

    @Data
    @AllArgsConstructor
    public static class SlotInfo {
        private UUID id;
        private UUID doctorId;
        private UUID clinicSpecialtyId;
        private LocalTime startTime;
        private LocalTime endTime;
        private AppointmentType appointmentType;
    }
}
