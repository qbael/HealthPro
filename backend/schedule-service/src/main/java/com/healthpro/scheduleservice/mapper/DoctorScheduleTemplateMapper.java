package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateResponseDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;

import java.time.DayOfWeek;
import java.util.List;

public class DoctorScheduleTemplateMapper {
    public static DoctorScheduleTemplateResponseDTO toDoctorScheduleTemplateResponseDTO(
            List<DoctorScheduleTemplate> doctorScheduleTemplates
    ) {
        DoctorScheduleTemplateResponseDTO doctorScheduleTemplateResponseDTO = new DoctorScheduleTemplateResponseDTO();
        DayOfWeek[] days = doctorScheduleTemplates.stream()
                .map(DoctorScheduleTemplate::getDayOfWeek)
                .toArray(DayOfWeek[]::new);

        doctorScheduleTemplateResponseDTO.setDayOfWeek(days);

        DoctorScheduleTemplate first = doctorScheduleTemplates.stream().findFirst().orElse(null);
        doctorScheduleTemplateResponseDTO.setFromTime(first.getFromTime());
        doctorScheduleTemplateResponseDTO.setToTime(first.getToTime());
        doctorScheduleTemplateResponseDTO.setSlotDuration(first.getSlotDuration());

        return doctorScheduleTemplateResponseDTO;
    }
}
