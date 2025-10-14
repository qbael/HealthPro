package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.ClinicRequestDTO;
import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.PatientRequestDTO;
import com.healthpro.authservice.entity.Clinic;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.ClinicMapper;
import com.healthpro.authservice.mapper.DoctorMapper;
import com.healthpro.authservice.repository.ClinicRepository;
import com.healthpro.authservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClinicService {
    private final ClinicRepository clinicRepository;
    private final UserRepository userRepository;

    public ClinicService(ClinicRepository clinicRepository, UserRepository userRepository) {
        this.clinicRepository = clinicRepository;
        this.userRepository = userRepository;
    }

    public List<ClinicResponseDTO> getClinics() {
        List<Clinic> clinics = clinicRepository.findAll();
        return clinics.stream().map(ClinicMapper::toClinicResponseDTO).toList();
    }

    public ClinicResponseDTO findByUserId(UUID id) {
        Clinic clinic = clinicRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return ClinicMapper.toClinicResponseDTO(clinic);
    }

    public void createClinic(User createdUser) {
        Clinic clinic = new Clinic();
        clinic.setUser(createdUser);
        clinicRepository.save(clinic);
    }

    @SuppressWarnings("DuplicatedCode")
    public void updateClinic(UUID id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicRepository.findByUser_Id(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(clinicRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("User with this email already exists" + clinicRequestDTO.getEmail());
        }

        User user = clinic.getUser();
        user.setEmail(clinicRequestDTO.getEmail());
        user.setPhoneNumber(clinicRequestDTO.getPhoneNumber());

        clinic.setClinicName(clinicRequestDTO.getClinicName());
        clinic.setAddress(clinicRequestDTO.getAddress());
        clinic.setDescription(clinicRequestDTO.getDescription());
        clinic.setLogoUrl(clinicRequestDTO.getLogoUrl());
        clinic.setAvatarUrl(clinicRequestDTO.getAvatarUrl());
        clinic.setWeekdayOpenHour(clinicRequestDTO.getWeekdayOpenHour());
        clinic.setWeekdayCloseHour(clinicRequestDTO.getWeekdayCloseHour());
        clinic.setWeekendOpenHour(clinicRequestDTO.getWeekendOpenHour());
        clinic.setWeekendCloseHour(clinicRequestDTO.getWeekendCloseHour());

        userRepository.save(user);
        clinicRepository.save(clinic);
    }
}
