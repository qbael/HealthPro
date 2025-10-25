package com.healthpro.clinicservice.mapper;

import com.healthpro.clinicservice.dto.ClinicSpecialtyResponseDTO;
import com.healthpro.clinicservice.entity.ClinicSpecialty;

public class ClinicSpecialtyDTOMapper {
    public static ClinicSpecialtyResponseDTO toDTO(ClinicSpecialty clinicSpecialty) {
        ClinicSpecialtyResponseDTO clinicSpecialtyResponseDTO = new ClinicSpecialtyResponseDTO();
        clinicSpecialtyResponseDTO.setId(clinicSpecialty.getId());
        clinicSpecialtyResponseDTO.setClinicId(clinicSpecialty.getClinic().getId());
        clinicSpecialtyResponseDTO.setSpecialtyId(clinicSpecialty.getSpecialty().getId());
        clinicSpecialtyResponseDTO.setSpecialtyName(clinicSpecialty.getSpecialty().getName());
        return clinicSpecialtyResponseDTO;
    }
}
