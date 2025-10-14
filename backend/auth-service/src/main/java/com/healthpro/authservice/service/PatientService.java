package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.PatientMapper;
import com.healthpro.authservice.repository.PatientRepository;
import com.healthpro.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final UserRepository userRepository;

    public PatientService(PatientRepository patientRepository, UserRepository userRepository) {
        this.patientRepository = patientRepository;
        this.userRepository = userRepository;
    }

    public List<PatientResponseDTO> getPatients() {
        List<Patient> patients = patientRepository.findAll();
        return patients.stream().map(PatientMapper::toPatientResponseDTO).toList();
    }

    public Optional<PatientResponseDTO> findByUserId(UUID id) {
        return patientRepository.findByUser_Id(id)
                .map(PatientMapper::toPatientResponseDTO);
    }

    public PatientResponseDTO createPatient(PatientRequestDTO patientRequestDTO) {
        Patient patient = new Patient();
        User user = userRepository.findById(patientRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        patient.setUser(user);
        patient.setFullName(patientRequestDTO.getFullName());
        patientRepository.save(patient);

        return PatientMapper.toPatientResponseDTO(patient);
    }
}
