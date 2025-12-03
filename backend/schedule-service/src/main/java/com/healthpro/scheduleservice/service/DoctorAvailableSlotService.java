package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Slf4j
@Service
public class DoctorAvailableSlotService {

    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;

    public DoctorAvailableSlotService(DoctorAvailableSlotRepository doctorAvailableSlotRepository,
                                      ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository) {
        this.doctorAvailableSlotRepository = doctorAvailableSlotRepository;
        this.clinicSpecialtyDoctorRepository = clinicSpecialtyDoctorRepository;
    }

    @Transactional
    public void deleteAvailableSlotsByTemplateIdIn(List<UUID> templateIds) {
        try {
            doctorAvailableSlotRepository.deleteAllByTemplateIdIn(templateIds);
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi khi xóa các khung giờ hẹn liên quan đến templateIds: " + e.getMessage());
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteSlotBeforeToday() {
        try {
            doctorAvailableSlotRepository.deleteByAppointmentDateLessThanEqual(LocalDate.now());
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi khi xóa các khung giờ hẹn trước ngày hôm nay: " + e.getMessage());
        }
    }

    @Transactional
    public void generateSlots(DoctorScheduleTemplate template, LocalDate date) {
        if (date.getDayOfWeek() != template.getDayOfWeek()) {
            return;
        }

        generateFromTemplateAndDate(template, date);
    }

    @Transactional
    public void generateSlots(DoctorScheduleTemplate template) {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(ScheduleGenerationService.DEFAULT_DAYS_AHEAD);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == template.getDayOfWeek()) {
                generateFromTemplateAndDate(template, date);
            }
        }
    }

    private void generateFromTemplateAndDate(DoctorScheduleTemplate template, LocalDate date) {
        LocalTime from = template.getFromTime();
        LocalTime to = template.getToTime();
        int duration = template.getSlotDuration();

        LocalTime slotStart = from;
        while (slotStart.plusMinutes(duration).isBefore(to) || slotStart.plusMinutes(duration).equals(to)) {
            LocalTime slotEnd = slotStart.plusMinutes(duration);

            DoctorAvailableSlot slot = DoctorAvailableSlot.builder()
                    .doctorId(template.getDoctorId())
                    .appointmentDate(date)
                    .startTime(slotStart)
                    .endTime(slotEnd)
                    .appointmentType(AppointmentType.DOCTOR)
                    .templateId(template.getId())
                    .build();
            try {
                doctorAvailableSlotRepository.save(slot);
            } catch (RuntimeException e) {
                throw new RuntimeException(e.getMessage());
            }

            slotStart = slotEnd;
        }
    }

    @Transactional
    public void generateSlots(ClinicSpecialtyScheduleTemplate template, LocalDate date) {
        if (date.getDayOfWeek() != template.getDayOfWeek()) {
            return;
        }

        List<ClinicSpecialtyDoctor> doctors = clinicSpecialtyDoctorRepository.findByClinicSpecialtyId(template.getClinicSpecialtyId());
        if (doctors.isEmpty()) {
            return;
        }

        generateFromTemplateAndDate(template, doctors, date);
    }

    @Transactional
    public void generateSlots(ClinicSpecialtyScheduleTemplate template) {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(ScheduleGenerationService.DEFAULT_DAYS_AHEAD);
        List<ClinicSpecialtyDoctor> doctors = clinicSpecialtyDoctorRepository.findByClinicSpecialtyId(template.getClinicSpecialtyId());
        if (doctors.isEmpty()) {
            return;
        }

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == template.getDayOfWeek()) {
                generateFromTemplateAndDate(template, doctors, date);
            }
        }
    }

    private void generateFromTemplateAndDate(ClinicSpecialtyScheduleTemplate template, List<ClinicSpecialtyDoctor> doctors, LocalDate date) {
        LocalTime from = template.getFromTime();
        LocalTime to = template.getToTime();
        int duration = template.getSlotDuration();

        LocalTime slotStart = from;
        while (slotStart.plusMinutes(duration).isBefore(to) || slotStart.plusMinutes(duration).equals(to)) {
            LocalTime slotEnd = slotStart.plusMinutes(duration);

            for(ClinicSpecialtyDoctor doctor : doctors) {
                DoctorAvailableSlot slot = DoctorAvailableSlot.builder()
                        .doctorId(doctor.getDoctorId())
                        .clinicSpecialtyId(template.getClinicSpecialtyId())
                        .appointmentDate(date)
                        .startTime(slotStart)
                        .endTime(slotEnd)
                        .appointmentType(AppointmentType.CLINIC)
                        .templateId(template.getId())
                        .build();
                try {
                    doctorAvailableSlotRepository.save(slot);
                } catch (RuntimeException e) {
                    throw new RuntimeException(e.getMessage());
                }
            }
            slotStart = slotEnd;
        }
    }

    public Optional<List<LocalDate>> getAvailableDatesByDoctorId(UUID doctorId) {
        return Optional.ofNullable(doctorAvailableSlotRepository.findAllDoctorAvailableDates(doctorId));
    }

    public Optional<List<AvailableTimeSlot>> getDoctorTypeAvailableSlotsByDoctorId(UUID doctorId, LocalDate appointmentDate) {
        return Optional.of(doctorAvailableSlotRepository
                .findAllByDoctorIdAndAppointmentTypeAndAppointmentDate(doctorId, AppointmentType.DOCTOR, appointmentDate)
                .stream().sorted(Comparator.comparing(AvailableTimeSlot::getStartTime)).toList());
    }

    public Optional<Map<LocalDate, Long>> getFastAvailableDatesByDoctorId(UUID doctorId) {
        List<Object[]> results = doctorAvailableSlotRepository.findFastAvailableDatesByDoctorId(doctorId);

        Map<LocalDate, Long> map = new LinkedHashMap<>();
        for (Object[] row : results) {
            LocalDate date = (LocalDate) row[0];
            Long count = (Long) row[1];
            map.put(date, count);
        }
        return Optional.of(map);
    }

    public long countSlots(){
        return doctorAvailableSlotRepository.count();
    }

    public LocalDate getMaxDate(){
        return doctorAvailableSlotRepository.findMaxAppointmentDate();
    }

    @Transactional
    public boolean deleteAvailableSlot(UUID slotId) {
        try {
            doctorAvailableSlotRepository.deleteById(slotId);
            return true;
        } catch (RuntimeException e) {
            log.error("Lỗi khi xóa khung giờ khả dụng với slotId {}: {}", slotId, e.getMessage());
            return false;
        }
    }

    @Transactional
    public void deleteAvailableSlotsByTemplateId(UUID id) {
        doctorAvailableSlotRepository.deleteAllByTemplateId(id);
    }

    @Transactional
    public void generateSlots(ClinicSpecialtyScheduleTemplate template, UUID doctorId) {
        LocalDate startDate = LocalDate.now().plusDays(1);
        LocalDate endDate = startDate.plusDays(ScheduleGenerationService.DEFAULT_DAYS_AHEAD);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == template.getDayOfWeek()) {
                LocalTime from = template.getFromTime();
                LocalTime to = template.getToTime();
                int duration = template.getSlotDuration();

                LocalTime slotStart = from;
                while (slotStart.plusMinutes(duration).isBefore(to) || slotStart.plusMinutes(duration).equals(to)) {
                    LocalTime slotEnd = slotStart.plusMinutes(duration);

                    DoctorAvailableSlot slot = DoctorAvailableSlot.builder()
                            .doctorId(doctorId)
                            .clinicSpecialtyId(template.getClinicSpecialtyId())
                            .appointmentDate(date)
                            .startTime(slotStart)
                            .endTime(slotEnd)
                            .appointmentType(AppointmentType.CLINIC)
                            .templateId(template.getId())
                            .build();
                    try {
                        doctorAvailableSlotRepository.save(slot);
                    } catch (RuntimeException e) {
                        throw new RuntimeException(e.getMessage());
                    }
                    slotStart = slotEnd;
                }
            }
        }
        log.info("Đã tạo khung giờ hẹn cho bác sĩ với ID {} từ template chuyên khoa phòng khám với ID {}", doctorId, template.getId());
    }

    public void deleteAvailableSlotsByTemplateIdAndDoctorId(UUID id, UUID doctorId) {
        doctorAvailableSlotRepository.deleteAllByTemplateIdAndDoctorId(id, doctorId);
    }
}
