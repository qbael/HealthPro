package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyScheduleTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@AllArgsConstructor
@Service
public class ClinicSpecialtyScheduleTemplateService {
    private final ClinicSpecialtyScheduleTemplateRepository clinicSpecialtyScheduleTemplateRepository;

    public void createClinicSpecialtyScheduleTemplate(
            UUID userId, ClinicSpecialtyScheduleTemplateRequestDTO clinicSpecialtyScheduleTemplateRequestDTO
    ) {
        clinicSpecialtyScheduleTemplateRepository.deleteByClinicSpecialtyId(userId);

        for (int i = 0; i < clinicSpecialtyScheduleTemplateRequestDTO.getDayOfWeek().length; i++) {
            ClinicSpecialtyScheduleTemplate clinicSpecialtyScheduleTemplate = new ClinicSpecialtyScheduleTemplate();
            clinicSpecialtyScheduleTemplate.setClinicSpecialtyId(userId);
            clinicSpecialtyScheduleTemplate.setDayOfWeek(clinicSpecialtyScheduleTemplateRequestDTO.getDayOfWeek()[i]);
            clinicSpecialtyScheduleTemplate.setFromTime(clinicSpecialtyScheduleTemplateRequestDTO.getFromTime());
            clinicSpecialtyScheduleTemplate.setToTime(clinicSpecialtyScheduleTemplateRequestDTO.getToTime());
            clinicSpecialtyScheduleTemplate.setSlotDuration(clinicSpecialtyScheduleTemplateRequestDTO.getSlotDuration());
            clinicSpecialtyScheduleTemplateRepository.save(clinicSpecialtyScheduleTemplate);
        }
    }
}
