package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;

import java.time.DayOfWeek;
import java.util.List;

public class DoctorScheduleTemplateMapper {
    public static DoctorScheduleTemplateDTO toDoctorScheduleTemplateResponseDTO(
            List<DoctorScheduleTemplate> doctorScheduleTemplates
    ) {
        DoctorScheduleTemplateDTO doctorScheduleTemplateDTO = new DoctorScheduleTemplateDTO();
        DayOfWeek[] days = doctorScheduleTemplates.stream()
                .map(DoctorScheduleTemplate::getDayOfWeek)
                .toArray(DayOfWeek[]::new);

        doctorScheduleTemplateDTO.setDayOfWeek(days);

        DoctorScheduleTemplate first = doctorScheduleTemplates.stream().findFirst().orElse(null);
        doctorScheduleTemplateDTO.setFromTime(first.getFromTime());
        doctorScheduleTemplateDTO.setToTime(first.getToTime());
        doctorScheduleTemplateDTO.setSlotDuration(first.getSlotDuration());

        return doctorScheduleTemplateDTO;
    }
}
