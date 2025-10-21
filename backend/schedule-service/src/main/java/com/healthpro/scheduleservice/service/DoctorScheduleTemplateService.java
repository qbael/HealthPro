package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final DoctorScheduleGenerator doctorScheduleGenerator;

    public void createDoctorScheduleTemplate(
            UUID userId, DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
        doctorScheduleTemplateRepository.deleteByDoctorId(userId);

        for (int i = 0; i < doctorScheduleTemplateRequestDTO.getDayOfWeek().length; i++) {
            DoctorScheduleTemplate doctorScheduleTemplate = new DoctorScheduleTemplate();
            doctorScheduleTemplate.setDoctorId(userId);
            doctorScheduleTemplate.setDayOfWeek(doctorScheduleTemplateRequestDTO.getDayOfWeek()[i]);
            doctorScheduleTemplate.setFromTime(doctorScheduleTemplateRequestDTO.getFromTime());
            doctorScheduleTemplate.setToTime(doctorScheduleTemplateRequestDTO.getToTime());
            doctorScheduleTemplate.setSlotDuration(doctorScheduleTemplateRequestDTO.getSlotDuration());
            doctorScheduleTemplateRepository.save(doctorScheduleTemplate);
        }
    }

    public void deleteAll () {
        doctorScheduleTemplateRepository.deleteAll();
    }
}
