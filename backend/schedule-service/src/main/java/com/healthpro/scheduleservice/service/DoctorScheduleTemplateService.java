package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.exception.ScheduleNotFoundException;
import com.healthpro.scheduleservice.exception.UserNotFoundException;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final DoctorScheduleGenerator doctorScheduleGenerator;

    public void createDoctorScheduleTemplate(
            UUID userId, DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
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

    public Map<DayOfWeek, List<DoctorAvailableSlot>> getWeeklyCalendar(UUID doctorId, LocalDate weekStart) {
        List<DoctorScheduleTemplate> doctorScheduleTemplates = doctorScheduleTemplateRepository.findByDoctorIdAndIsActiveTrue(doctorId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule is empty with this ID " + doctorId));

        Map<DayOfWeek, List<DoctorAvailableSlot>> calendar = new LinkedHashMap<>();

        for (DoctorScheduleTemplate template : doctorScheduleTemplates) {
            List<DoctorAvailableSlot> doctorSlots = doctorScheduleGenerator.generateDoctorSchedule(template, weekStart);
            calendar.put(template.getDayOfWeek(), doctorSlots);
        }

        return calendar;
    }

    public void deleteAll () {
        doctorScheduleTemplateRepository.deleteAll();
    }
}
