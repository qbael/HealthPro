package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;

import java.time.DayOfWeek;
import java.util.List;

public class ClinicSpecialtyScheduleTemplateMapper {
    public static ClinicSpecialtyScheduleTemplateDTO toClinicSpecialtyScheduleTemplateResponseDTO(
            List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyScheduleTemplates
    ) {
        ClinicSpecialtyScheduleTemplateDTO clinicSpecialtyScheduleTemplateDTO = new ClinicSpecialtyScheduleTemplateDTO();
        DayOfWeek[] days = clinicSpecialtyScheduleTemplates.stream()
                .map(ClinicSpecialtyScheduleTemplate::getDayOfWeek)
                .toArray(DayOfWeek[]::new);

        clinicSpecialtyScheduleTemplateDTO.setDayOfWeek(days);

        ClinicSpecialtyScheduleTemplate first = clinicSpecialtyScheduleTemplates.stream().findFirst().orElse(null);
        clinicSpecialtyScheduleTemplateDTO.setFromTime(first.getFromTime());
        clinicSpecialtyScheduleTemplateDTO.setToTime(first.getToTime());
        clinicSpecialtyScheduleTemplateDTO.setSlotDuration(first.getSlotDuration());

        return clinicSpecialtyScheduleTemplateDTO;
    }
}
