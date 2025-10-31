package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.mapper.DoctorScheduleTemplateMapper;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final ScheduleGenerationService scheduleGenerationService;

    public DoctorScheduleTemplateDTO getAllDoctorScheduleTemplates(UUID doctorId) {
        List<DoctorScheduleTemplate> doctorScheduleTemplates = doctorScheduleTemplateRepository.findByDoctorId(doctorId);

        if (doctorScheduleTemplates.isEmpty()) {
            return new DoctorScheduleTemplateDTO();
        }

        return DoctorScheduleTemplateMapper.toDoctorScheduleTemplateResponseDTO(doctorScheduleTemplates);
    }

    @Transactional
    public void createDoctorScheduleTemplate(
            UUID doctorId, DoctorScheduleTemplateDTO doctorScheduleTemplateDTO
    ) {
        doctorScheduleTemplateRepository.deleteByDoctorId(doctorId);

        for (int i = 0; i < doctorScheduleTemplateDTO.getDayOfWeek().length; i++) {
            DoctorScheduleTemplate doctorScheduleTemplate = new DoctorScheduleTemplate();
            doctorScheduleTemplate.setDoctorId(doctorId);
            doctorScheduleTemplate.setDayOfWeek(doctorScheduleTemplateDTO.getDayOfWeek()[i]);
            doctorScheduleTemplate.setFromTime(doctorScheduleTemplateDTO.getFromTime());
            doctorScheduleTemplate.setToTime(doctorScheduleTemplateDTO.getToTime());
            doctorScheduleTemplate.setSlotDuration(doctorScheduleTemplateDTO.getSlotDuration());
            doctorScheduleTemplateRepository.save(doctorScheduleTemplate);
        }

        scheduleGenerationService.generateSlotFromDoctorTemplate();
    }

    public void deleteAll () {
        doctorScheduleTemplateRepository.deleteAll();
    }
}
