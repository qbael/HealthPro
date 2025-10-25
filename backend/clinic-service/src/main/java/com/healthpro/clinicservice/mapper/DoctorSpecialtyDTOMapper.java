package com.healthpro.clinicservice.mapper;

import com.healthpro.clinicservice.dto.DoctorSpecialtyResponseDTO;
import com.healthpro.clinicservice.entity.DoctorSpecialty;

public class DoctorSpecialtyDTOMapper {
    public static DoctorSpecialtyResponseDTO toDTO(DoctorSpecialty doctorSpecialty) {
        DoctorSpecialtyResponseDTO doctorSpecialtyResponseDTO = new DoctorSpecialtyResponseDTO();
        doctorSpecialtyResponseDTO.setId(doctorSpecialty.getId());
        doctorSpecialtyResponseDTO.setDoctorId(doctorSpecialty.getDoctor().getId());
        doctorSpecialtyResponseDTO.setSpecialtyId(doctorSpecialty.getSpecialty().getId());
        doctorSpecialtyResponseDTO.setSpecialtyName(doctorSpecialty.getSpecialty().getName());
        return doctorSpecialtyResponseDTO;
    }
}
