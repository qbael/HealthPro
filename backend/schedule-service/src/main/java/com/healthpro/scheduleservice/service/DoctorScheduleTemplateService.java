package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateRequestDTO;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.exception.ScheduleNotFoundException;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final DoctorScheduleGenerator doctorScheduleGenerator;
//    private final DoctorRepository doctorRepository;

    public void createDoctorScheduleTemplate(
            UUID userId, DoctorScheduleTemplateRequestDTO doctorScheduleTemplateRequestDTO
    ) {
//        Doctor doctor = doctorRepository.getDoctorByUser_Id(userId);

        for (int i = 0; i < doctorScheduleTemplateRequestDTO.getDayOfWeek().length; i++) {
            DoctorScheduleTemplate doctorScheduleTemplate = new DoctorScheduleTemplate();
            doctorScheduleTemplate.setDoctorId(userId);
//            doctorScheduleTemplate.setDoctorId(doctor.getId());
            doctorScheduleTemplate.setDayOfWeek(doctorScheduleTemplateRequestDTO.getDayOfWeek()[i]);
            doctorScheduleTemplate.setFromTime(doctorScheduleTemplateRequestDTO.getFromTime());
            doctorScheduleTemplate.setToTime(doctorScheduleTemplateRequestDTO.getToTime());
            doctorScheduleTemplate.setSlotDuration(doctorScheduleTemplateRequestDTO.getSlotDuration());
            doctorScheduleTemplateRepository.save(doctorScheduleTemplate);
        }
    }

    public Map<DayOfWeek, List<LocalDateTime>> getWeeklyCalendar(UUID doctorId, LocalDate weekStart) {
        List<DoctorScheduleTemplate> templates = doctorScheduleTemplateRepository.findByDoctorIdAndIsActiveTrue(doctorId)
                .orElseThrow(() -> new ScheduleNotFoundException("Schedule is empty with this ID " + doctorId));

        Map<DayOfWeek, List<LocalDateTime>> calendar = new LinkedHashMap<>();

        for (DoctorScheduleTemplate template : templates) {
            List<LocalDateTime> doctorSlots = doctorScheduleGenerator.generateDoctorSchedule(template, weekStart);
            calendar.put(template.getDayOfWeek(), doctorSlots);
        }

        return calendar;
    }
}
