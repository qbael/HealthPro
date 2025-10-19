package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorScheduleGenerator {
    public List<LocalDateTime> generateDoctorSchedule(
            DoctorScheduleTemplate doctorScheduleTemplate,
            LocalDate weekStartDate) {
        List<LocalDateTime> doctorSlots = new ArrayList<>();

        LocalDate targetDate = weekStartDate.with(doctorScheduleTemplate.getDayOfWeek());
        LocalTime toTime = doctorScheduleTemplate.getToTime();
        LocalTime currentTime = doctorScheduleTemplate.getFromTime();

        while (currentTime.isBefore(toTime)) {
            doctorSlots.add(LocalDateTime.of(targetDate, currentTime));
            currentTime = currentTime.plusMinutes(doctorScheduleTemplate.getSlotDuration());
        }

        return doctorSlots;
    }
}
