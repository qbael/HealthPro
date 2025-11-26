package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.dto.PatientResponseDTO;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.PatientMapper;
import com.healthpro.authservice.repository.PatientRepository;
import com.healthpro.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<PatientResponseDTO> getPatients(Pageable pageable) {
        Page<Patient> patients = patientRepository.findAll(pageable);
        return patients.map(PatientMapper::toPatientResponseDTO);
    }

    public PatientResponseDTO findByUserId(UUID id) {
        Patient patient = patientRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return PatientMapper.toPatientResponseDTO(patient);
    }

    @Transactional
    public UUID createPatient(User createdUser) {
        Patient patient = new Patient();
        patient.setUser(createdUser);
        patientRepository.save(patient);
        return patient.getId();
    }

    @SuppressWarnings("DuplicatedCode")
    public void updatePatient(UUID id, PatientRequestDTO patientRequestDTO) {
        Patient patient = patientRepository.findByUser_Id(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(patientRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email này đã tồn tại");
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

    public Boolean isFullyRegistered(UUID userId) {
        Patient patient = patientRepository.findByUser_Id(userId)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + userId + " not found"
                ));
        User user = patient.getUser();

        return user.getEmail() != null &&
                user.getPhoneNumber() != null &&
                patient.getFullName() != null &&
                patient.getDateOfBirth() != null &&
                patient.getGender() != null;
    }

    public Optional<Patient> getPatientById(UUID patientId) {
        return patientRepository.findById(patientId);
    }
}
