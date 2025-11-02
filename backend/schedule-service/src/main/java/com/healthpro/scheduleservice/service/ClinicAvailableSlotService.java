package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

@Service
public class ClinicAvailableSlotService {
    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;

    public ClinicAvailableSlotService(DoctorAvailableSlotRepository doctorAvailableSlotRepository) {
        this.doctorAvailableSlotRepository = doctorAvailableSlotRepository;
    }

    public Optional<List<LocalDate>> getClinicSpecialtyAvailableDates(UUID clinicSpecialtyId) {
        return Optional.of(doctorAvailableSlotRepository.findAllClinicSpecialtyAvailableDates(clinicSpecialtyId));
    }

    public Optional<List<AvailableTimeSlot>> getClinicTypeAvailableSlotsByClinicSpecialtyId(UUID clinicSpecialtyId, LocalDate appointmentDate) {
        List<AvailableTimeSlot> slots = doctorAvailableSlotRepository.findAllByClinicSpecialtyIdAndAppointmentTypeAndAppointmentDate(clinicSpecialtyId, AppointmentType.CLINIC, appointmentDate);
        Map<LocalTime, AvailableTimeSlot> slotMap = new TreeMap<>();

        for (AvailableTimeSlot slot : slots) {
            slotMap.put(slot.getStartTime(), slot);
        }
        return Optional.of(new ArrayList<>(slotMap.values()));
    }
}
