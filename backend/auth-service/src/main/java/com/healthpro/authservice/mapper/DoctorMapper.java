package com.healthpro.authservice.mapper;

import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.UserResponseDTO;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.User;

public class DoctorMapper {
    public static DoctorResponseDTO toDoctorResponseDTO(Doctor doctor) {
        DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
        doctorDTO.setId(doctor.getId());
        doctorDTO.setEmail(doctor.getEmail());
        doctorDTO.setPhoneNumber(doctor.getPhoneNumber());
        doctorDTO.setRole(doctor.getRole());
        doctorDTO.setIsActive(doctor.getIsActive());

        return doctorDTO;
    }
}
