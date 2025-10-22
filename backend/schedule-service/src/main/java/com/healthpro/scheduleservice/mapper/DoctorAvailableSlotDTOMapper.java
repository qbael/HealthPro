package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.DoctorAvailableSlotDTO;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DoctorAvailableSlotDTOMapper {
    public static List<DoctorAvailableSlotDTO> toDoctorAvailableSlotDTO(
            List<DoctorAvailableSlot> doctorAvailableSlots
    ) {
        Map<LocalDate, List<DoctorAvailableSlotDTO.SlotInfo>> grouped = doctorAvailableSlots.stream()
                .map(slot -> new DoctorAvailableSlotDTO.SlotInfo(
                        slot.getId(),
                        slot.getDoctorId(),
                        slot.getClinicSpecialtyId(),
                        slot.getStartTime(),
                        slot.getEndTime(),
                        slot.getAppointmentType()
                ))
                .collect(Collectors.groupingBy(slotInfo ->
                        doctorAvailableSlots.stream()
                                .filter(s -> s.getId().equals(slotInfo.getId()))
                                .findFirst()
                                .map(DoctorAvailableSlot::getAppointmentDate)
                                .orElse(null)
                ));

        return grouped.entrySet().stream()
                .map(entry -> new DoctorAvailableSlotDTO(entry.getKey(), entry.getValue()))
                .sorted(Comparator.comparing(DoctorAvailableSlotDTO::getAppointmentDate))
                .toList();
    }
}
