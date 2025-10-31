package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.CheckClinicSpecialtyScheduleDTO;
import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.mapper.ClinicSpecialtyScheduleTemplateMapper;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyScheduleTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClinicSpecialtyScheduleTemplateService {
    private final ClinicSpecialtyScheduleTemplateRepository clinicSpecialtyScheduleTemplateRepository;
    private final ScheduleGenerationService scheduleGenerationService;

    public ClinicSpecialtyScheduleTemplateDTO getAllClinicSpecialtyScheduleTemplates(UUID clinicSpecialtyId) {
        List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyScheduleTemplates = clinicSpecialtyScheduleTemplateRepository.findByClinicSpecialtyId(clinicSpecialtyId);

        if (clinicSpecialtyScheduleTemplates.isEmpty()) {
            return new ClinicSpecialtyScheduleTemplateDTO();
        }

        return ClinicSpecialtyScheduleTemplateMapper.toClinicSpecialtyScheduleTemplateResponseDTO(clinicSpecialtyScheduleTemplates);
    }

    public boolean checkClinicSpecialtyScheduleExists(UUID clinicSpecialtyId) {
        return clinicSpecialtyScheduleTemplateRepository.existsByClinicSpecialtyId(clinicSpecialtyId);
    }

    @Transactional
    public void createClinicSpecialtyScheduleTemplate(
            UUID clinicSpecialtyId, ClinicSpecialtyScheduleTemplateDTO clinicSpecialtyScheduleTemplateDTO
    ) {
        clinicSpecialtyScheduleTemplateRepository.deleteByClinicSpecialtyId(clinicSpecialtyId);

        for (int i = 0; i < clinicSpecialtyScheduleTemplateDTO.getDayOfWeek().length; i++) {
            ClinicSpecialtyScheduleTemplate clinicSpecialtyScheduleTemplate = new ClinicSpecialtyScheduleTemplate();
            clinicSpecialtyScheduleTemplate.setClinicSpecialtyId(clinicSpecialtyId);
            clinicSpecialtyScheduleTemplate.setDayOfWeek(clinicSpecialtyScheduleTemplateDTO.getDayOfWeek()[i]);
            clinicSpecialtyScheduleTemplate.setFromTime(clinicSpecialtyScheduleTemplateDTO.getFromTime());
            clinicSpecialtyScheduleTemplate.setToTime(clinicSpecialtyScheduleTemplateDTO.getToTime());
            clinicSpecialtyScheduleTemplate.setSlotDuration(clinicSpecialtyScheduleTemplateDTO.getSlotDuration());
            clinicSpecialtyScheduleTemplateRepository.save(clinicSpecialtyScheduleTemplate);
        }

        scheduleGenerationService.generateSlotFromDoctorTemplate();
    }
}
