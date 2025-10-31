package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.ClinicRequestDTO;
import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.dto.ClinicResponseWebClientDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.entity.Clinic;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.ResourceNotFoundException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.ClinicMapper;
import com.healthpro.authservice.mapper.DoctorMapper;
import com.healthpro.authservice.repository.ClinicRepository;
import com.healthpro.authservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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

    public Page<ClinicResponseDTO> getClinics(Pageable pageable) {
        Page<Clinic> clinics = clinicRepository.findAll(pageable);
        return clinics.map(ClinicMapper::toClinicResponseDTO);
    }

    public ClinicResponseDTO findByUserId(UUID id) {
        Clinic clinic = clinicRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return ClinicMapper.toClinicResponseDTO(clinic);
    }

    public UUID createClinic(User createdUser) {
        Clinic clinic = new Clinic();
        clinic.setUser(createdUser);
        clinicRepository.save(clinic);
        return  clinic.getId();
    }

    @SuppressWarnings("DuplicatedCode")
    public void updateClinic(UUID id, ClinicRequestDTO clinicRequestDTO) {
        Clinic clinic = clinicRepository.findByUser_Id(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(clinicRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email này đã tồn tại");
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

    public ClinicResponseWebClientDTO getClinicById(UUID id) {
        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found clinic"));

        ClinicResponseWebClientDTO dto = new ClinicResponseWebClientDTO();

        dto.setId(clinic.getId());
        dto.setUserId(clinic.getUser().getId());
        dto.setClinicName(clinic.getClinicName());
        dto.setAddress(clinic.getAddress());
        dto.setDescription(clinic.getDescription());
        dto.setWeekdayOpenHour(clinic.getWeekdayOpenHour());
        dto.setWeekdayCloseHour(clinic.getWeekdayCloseHour());
        dto.setWeekendOpenHour(clinic.getWeekendOpenHour());
        dto.setWeekendCloseHour(clinic.getWeekendCloseHour());
        dto.setLogoUrl(clinic.getLogoUrl());
        dto.setAvatarUrl(clinic.getAvatarUrl());

        return dto;
    }

    public Optional<Clinic> getClinicByIdForAppointment(UUID id) {
        return clinicRepository.findById(id);
    }
}
