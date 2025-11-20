package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;

import java.util.List;

public class DoctorScheduleTemplateMapper {
    public static List<DoctorScheduleTemplateDTO> toDoctorScheduleTemplateResponseDTO(
            List<DoctorScheduleTemplate> doctorScheduleTemplates) {
        return doctorScheduleTemplates.stream().map(template -> {
            DoctorScheduleTemplateDTO dto = new DoctorScheduleTemplateDTO();
            dto.setDayOfWeek(template.getDayOfWeek());
            dto.setFromTime(template.getFromTime());
            dto.setToTime(template.getToTime());
            dto.setSlotDuration(template.getSlotDuration());
            return dto;
        }).toList();
    }
}
