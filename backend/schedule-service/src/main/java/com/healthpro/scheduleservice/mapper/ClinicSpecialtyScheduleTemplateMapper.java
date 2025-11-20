package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;

import java.time.DayOfWeek;
import java.util.List;

public class ClinicSpecialtyScheduleTemplateMapper {
    public static List<ClinicSpecialtyScheduleTemplateDTO> toClinicSpecialtyScheduleTemplateResponseDTO(
            List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyScheduleTemplates
    ) {
        return clinicSpecialtyScheduleTemplates.stream().map(template -> {
            ClinicSpecialtyScheduleTemplateDTO dto = new ClinicSpecialtyScheduleTemplateDTO();
            dto.setDayOfWeek(template.getDayOfWeek());
            dto.setFromTime(template.getFromTime());
            dto.setToTime(template.getToTime());
            dto.setSlotDuration(template.getSlotDuration());
            return dto;
        }).toList();
    }
}
