package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicSpecialtyInfoResponseDto;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AppointmentService {
    private final ClinicService clinicService;

    public AppointmentService(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    public ClinicSpecialtyInfoResponseDto getClinicSpecialtyInfo(String specialtyId) {
        Optional<ClinicSpecialty> clinicSpecialtyOpt = clinicService.getClinicSpecialtyById(specialtyId);
        if (clinicSpecialtyOpt.isEmpty()) {
            throw new IllegalArgumentException("Clinic specialty with id " + specialtyId + " not found");
        }
        ClinicSpecialty clinicSpecialty = clinicSpecialtyOpt.get();
        return new ClinicSpecialtyInfoResponseDto(
                clinicSpecialty.getId(),
                clinicSpecialty.getSpecialty().getName(),
                clinicSpecialty.getClinic().getId()
        );
    }
}
