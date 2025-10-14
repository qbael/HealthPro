package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.PatientMapper;
import com.healthpro.authservice.repository.PatientRepository;
import com.healthpro.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
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

    public PatientResponseDTO findByUserId(UUID id) {
        Patient patient = patientRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return PatientMapper.toPatientResponseDTO(patient);
    }

    @Transactional
    public void createPatient(User createdUser) {
        Patient patient = new Patient();
        patient.setUser(createdUser);
        patientRepository.save(patient);
    }
}
