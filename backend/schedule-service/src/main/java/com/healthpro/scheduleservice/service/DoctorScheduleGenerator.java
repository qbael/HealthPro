package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class DoctorScheduleGenerator {
    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;

//    public List<LocalDateTime> generateDoctorSchedule(
//            DoctorScheduleTemplate doctorScheduleTemplate,
//            LocalDate weekStartDate) {
//        List<LocalDateTime> doctorSlots = new ArrayList<>();
//
//        LocalDate targetDate = weekStartDate.with(doctorScheduleTemplate.getDayOfWeek());
//        LocalTime toTime = doctorScheduleTemplate.getToTime();
//        LocalTime currentTime = doctorScheduleTemplate.getFromTime();
//
//        while (currentTime.isBefore(toTime)) {
//            doctorSlots.add(LocalDateTime.of(targetDate, currentTime));
//            currentTime = currentTime.plusMinutes(doctorScheduleTemplate.getSlotDuration());
//        }
//
//        return doctorSlots;
//    }

    public List<DoctorAvailableSlot> generateDoctorSchedule(
            DoctorScheduleTemplate doctorScheduleTemplate,
            LocalDate weekStartDate) {

        LocalDate lastWeekStart = weekStartDate.minusDays(7);
        LocalDate lastWeekEnd = weekStartDate.minusDays(1);

        doctorAvailableSlotRepository.deleteByDoctorIdAndAppointmentDateBetween(
                doctorScheduleTemplate.getDoctorId(),
                lastWeekStart,
                lastWeekEnd
        );

        List<DoctorAvailableSlot> doctorAvailableSlots = new ArrayList<>();

        LocalDate targetDate = weekStartDate.with(doctorScheduleTemplate.getDayOfWeek());
        LocalTime currentTime = doctorScheduleTemplate.getFromTime();
        LocalTime toTime = doctorScheduleTemplate.getToTime();

        while (currentTime.isBefore(toTime)) {
            LocalTime endTime = currentTime.plusMinutes(doctorScheduleTemplate.getSlotDuration());

            DoctorAvailableSlot doctorAvailableSlot = DoctorAvailableSlot.builder()
                    .doctorId(doctorScheduleTemplate.getDoctorId())
//                    .clinicSpecialtyId(doctorScheduleTemplate.getClinicSpecialtyId())
                    .appointmentDate(targetDate)
                    .startTime(currentTime)
                    .endTime(endTime)
                    .appointmentType(AppointmentType.DOCTOR)
                    .build();

            doctorAvailableSlots.add(doctorAvailableSlot);
            currentTime = endTime;
        }

        return doctorAvailableSlotRepository.saveAll(doctorAvailableSlots);
    }
}
