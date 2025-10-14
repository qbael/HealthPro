package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.PatientMapper;
import com.healthpro.authservice.mapper.UserMapper;
import com.healthpro.authservice.repository.PatientRepository;
import com.healthpro.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @SuppressWarnings("DuplicatedCode")
    public void updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findByUser_Id(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("User with this email already exists" + patientRequestDTO.getEmail());
        }

        User user = patient.getUser();
        user.setEmail(patientRequestDTO.getEmail());
        user.setPhoneNumber(patientRequestDTO.getPhoneNumber());

        patient.setFullName(patientRequestDTO.getFullName());
        patient.setDateOfBirth(patientRequestDTO.getDateOfBirth());
        patient.setGender(patientRequestDTO.getGender());
        patient.setMedicalNotes(patientRequestDTO.getMedicalNotes());
        patientRepository.save(patient);

        userRepository.save(user);
        patientRepository.save(patient);
    }
}
