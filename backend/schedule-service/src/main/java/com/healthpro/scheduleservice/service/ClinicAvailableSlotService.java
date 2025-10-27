package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.AvailableTimeSlot;
import com.healthpro.scheduleservice.entity.enums.AppointmentType;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClinicAvailableSlotService {
    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;

    public ClinicAvailableSlotService(DoctorAvailableSlotRepository doctorAvailableSlotRepository){
        this.doctorAvailableSlotRepository = doctorAvailableSlotRepository;
    }

    public Optional<List<LocalDate>> getClinicSpecialtyAvailableDates(UUID clinicSpecialtyId){
        return Optional.of(doctorAvailableSlotRepository.findAllClinicSpecialtyAvailableDates(clinicSpecialtyId));
    }

    public Optional<LinkedHashSet<AvailableTimeSlot>> getClinicTypeAvailableSlotsByClinicSpecialtyId(UUID clinicSpecialtyId, LocalDate appointmentDate) {
        return Optional.of(doctorAvailableSlotRepository.findAllByClinicSpecialtyIdAndAppointmentTypeAndAppointmentDate(clinicSpecialtyId, AppointmentType.CLINIC, appointmentDate));
    }
}
