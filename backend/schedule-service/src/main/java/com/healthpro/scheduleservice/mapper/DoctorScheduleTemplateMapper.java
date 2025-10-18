package com.healthpro.scheduleservice.mapper;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateResponseDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;

public class DoctorScheduleTemplateMapper {
    public DoctorScheduleTemplateResponseDTO toDoctorScheduleTemplateResponseDTO(
            DoctorScheduleTemplate doctorScheduleTemplate
    ) {
        DoctorScheduleTemplateResponseDTO doctorScheduleTemplateResponseDTO = new DoctorScheduleTemplateResponseDTO();
        doctorScheduleTemplateResponseDTO.setDayOfWeek(doctorScheduleTemplate.getDayOfWeek());
        doctorScheduleTemplateResponseDTO.setFromTime(doctorScheduleTemplate.getFromTime());
        doctorScheduleTemplateResponseDTO.setToTime(doctorScheduleTemplate.getToTime());
        doctorScheduleTemplateResponseDTO.setSlotDuration(doctorScheduleTemplate.getSlotDuration());
        return doctorScheduleTemplateResponseDTO;
    }
}
