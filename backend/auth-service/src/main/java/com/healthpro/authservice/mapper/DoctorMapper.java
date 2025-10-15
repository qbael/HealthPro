package com.healthpro.authservice.mapper;

import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.entity.Doctor;

public class DoctorMapper {
    public static DoctorResponseDTO toDoctorResponseDTO(Doctor doctor) {
        DoctorResponseDTO doctorDTO = new DoctorResponseDTO();
        doctorDTO.setId(doctor.getUser().getId());
        doctorDTO.setDoctorId(doctor.getId());
        doctorDTO.setRole(doctor.getUser().getRole().getRoleName());
        doctorDTO.setEmail(doctor.getUser().getEmail());
        doctorDTO.setPhoneNumber(doctor.getUser().getPhoneNumber());
        doctorDTO.setIsActive(doctor.getUser().getIsActive());
        doctorDTO.setFullName(doctor.getFullName());
        doctorDTO.setAddress(doctor.getAddress());
        doctorDTO.setBio(doctor.getBio());
        doctorDTO.setGender(doctor.getGender());
        doctorDTO.setAvatarUrl(doctor.getAvatarUrl());
        return doctorDTO;
    }
}
