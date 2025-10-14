package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.ClinicRequestDTO;
import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.entity.Clinic;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.ClinicMapper;
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

    public Optional<ClinicResponseDTO> findByUserId(UUID id) {
        return clinicRepository.findByUser_Id(id)
                .map(ClinicMapper::toClinicResponseDTO);
    }

    public ClinicResponseDTO createClinic(ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = new Clinic();
        User user = userRepository.findById(clinicRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        clinic.setUser(user);
        clinic.setClinicName(clinicRequestDTO.getClinicName());
        clinic.setAddress(clinicRequestDTO.getAddress());
        clinicRepository.save(clinic);

        return ClinicMapper.toClinicResponseDTO(clinic);
    }
}
