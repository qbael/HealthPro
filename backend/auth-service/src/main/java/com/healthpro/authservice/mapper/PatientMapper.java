package com.healthpro.authservice.mapper;

import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.entity.Patient;

public class PatientMapper {
    public static PatientResponseDTO toPatientResponseDTO(Patient patient) {
        PatientResponseDTO patientDTO = new PatientResponseDTO();
        patientDTO.setId(patient.getUser().getId());
        patientDTO.setPatientId(patient.getId());
        patientDTO.setRole(patient.getUser().getRole().getRoleName());
        patientDTO.setEmail(patient.getUser().getEmail());
        patientDTO.setPhoneNumber(patient.getUser().getPhoneNumber());
        patientDTO.setIsActive(patient.getUser().getIsActive());
        patientDTO.setFullName(patient.getFullName());
        patientDTO.setDateOfBirth(patient.getDateOfBirth());
        patientDTO.setGender(patient.getGender());
        patientDTO.setMedicalNotes(patient.getMedicalNotes());
        return patientDTO;
    }
}
