package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.exception.ResourceNotFoundException;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class DoctorAvailableSlotService {
    public static final int DEFAULT_DAYS_AHEAD = 14;

    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;

    public DoctorAvailableSlotService(DoctorAvailableSlotRepository doctorAvailableSlotRepository,
                                      ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository) {
        this.doctorAvailableSlotRepository = doctorAvailableSlotRepository;
        this.clinicSpecialtyDoctorRepository = clinicSpecialtyDoctorRepository;
    }

    public Optional<List<DoctorAvailableSlot>> getAllSlot(UUID userRoleId, AppointmentType appoinmentType) {
        return Optional.ofNullable(doctorAvailableSlotRepository.findByDoctorIdAndAppointmentType(userRoleId, appoinmentType).orElseThrow(
                () -> new ResourceNotFoundException("Slot not found")));
    }

    @Transactional(rollbackFor = RuntimeException.class)
    public void deleteSlotBeforeToday() {
        try {
            doctorAvailableSlotRepository.deleteByAppointmentDateBefore(LocalDate.now()); //mới thêm vào repo theo cái của ông
        } catch (RuntimeException e) {
            throw new RuntimeException("Lỗi khi xóa các khung giờ hẹn trước ngày hôm nay: " + e.getMessage());
        }
    }

    @Transactional
    public void generateSlots(DoctorScheduleTemplate template) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(DEFAULT_DAYS_AHEAD);

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == template.getDayOfWeek()) {
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
    }

    @Transactional
    public void generateSlots(ClinicSpecialtyScheduleTemplate template) {
        LocalDate startDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(DEFAULT_DAYS_AHEAD);
        List<ClinicSpecialtyDoctor> doctors = clinicSpecialtyDoctorRepository.findByClinicSpecialtyId(template.getClinicSpecialtyId());
        if (doctors.isEmpty()) {
            return;
        }

        for (LocalDate date = startDate; !date.isAfter(endDate); date = date.plusDays(1)) {
            if (date.getDayOfWeek() == template.getDayOfWeek()) {
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
        }
    }
}
