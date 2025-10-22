package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateResponseDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.mapper.DoctorScheduleTemplateMapper;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final ScheduleGenerationService scheduleGenerationService;

    public DoctorScheduleTemplateResponseDTO getAllDoctorScheduleTemplates(UUID doctorId) {
        List<DoctorScheduleTemplate> doctorScheduleTemplates = doctorScheduleTemplateRepository.findByDoctorId(doctorId);

        if (doctorScheduleTemplates.isEmpty()) {
            return new DoctorScheduleTemplateResponseDTO();
        }

        return DoctorScheduleTemplateMapper.toDoctorScheduleTemplateResponseDTO(doctorScheduleTemplates);
    }

    public void createDoctorScheduleTemplate(
            UUID userRoleId, DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
        doctorScheduleTemplateRepository.deleteByDoctorId(userRoleId);

        for (int i = 0; i < doctorScheduleTemplateRequestDTO.getDayOfWeek().length; i++) {
            DoctorScheduleTemplate doctorScheduleTemplate = new DoctorScheduleTemplate();
            doctorScheduleTemplate.setDoctorId(userRoleId);
            doctorScheduleTemplate.setDayOfWeek(doctorScheduleTemplateRequestDTO.getDayOfWeek()[i]);
            doctorScheduleTemplate.setFromTime(doctorScheduleTemplateRequestDTO.getFromTime());
            doctorScheduleTemplate.setToTime(doctorScheduleTemplateRequestDTO.getToTime());
            doctorScheduleTemplate.setSlotDuration(doctorScheduleTemplateRequestDTO.getSlotDuration());
            doctorScheduleTemplateRepository.save(doctorScheduleTemplate);
        }

        scheduleGenerationService.generateSlotFromDoctorTemplate();
    }

    public void deleteAll () {
        doctorScheduleTemplateRepository.deleteAll();
    }
}
