package com.healthpro.authservice.mapper;

import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.entity.Clinic;

public class ClinicMapper {
    public static ClinicResponseDTO toClinicResponseDTO(Clinic clinic) {
        ClinicResponseDTO clinicDTO = new ClinicResponseDTO();
        clinicDTO.setId(clinic.getUser().getId());
        clinicDTO.setClinicId(clinic.getId());
        clinicDTO.setRole(clinic.getUser().getRole().getRoleName());
        clinicDTO.setEmail(clinic.getUser().getEmail());
        clinicDTO.setPhoneNumber(clinic.getUser().getPhoneNumber());
        clinicDTO.setIsActive(clinic.getUser().getIsActive());
        clinicDTO.setClinicName(clinic.getClinicName());
        return clinicDTO;
    }
}
