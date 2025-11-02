package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.DoctorResponseWebClientDTO;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.ResourceNotFoundException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.DoctorMapper;
import com.healthpro.authservice.repository.DoctorRepository;
import com.healthpro.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public Page<DoctorResponseDTO> getDoctors(Pageable pageable) {
        Page<Doctor> doctors = doctorRepository.findAll(pageable);
        return doctors.map(DoctorMapper::toDoctorResponseDTO);
    }

    public DoctorResponseDTO findByUserId(UUID id) {
        Doctor doctor = doctorRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return DoctorMapper.toDoctorResponseDTO(doctor);
    }

    @Transactional
    public UUID createDoctor(User createdUser) {
        Doctor doctor = new Doctor();
        doctor.setUser(createdUser);
        doctorRepository.save(doctor);
        return doctor.getId();
    }

    public void updateDoctor(UUID id, DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = doctorRepository.findByUser_Id(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(doctorRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("Email này đã tồn tại");
        }

        User user = doctor.getUser();
        user.setEmail(doctorRequestDTO.getEmail());
        user.setPhoneNumber(doctorRequestDTO.getPhoneNumber());

        doctor.setFullName(doctorRequestDTO.getFullName());
        doctor.setBio(doctorRequestDTO.getBio());
        doctor.setGender(doctorRequestDTO.getGender());
        doctor.setAddress(doctorRequestDTO.getAddress());
        doctor.setAvatarUrl(doctorRequestDTO.getAvatarUrl());

        userRepository.save(user);
        doctorRepository.save(doctor);
    }

    public DoctorResponseWebClientDTO getDoctorById(UUID id) {
        Doctor doctor = doctorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found doctor"));

        DoctorResponseWebClientDTO dto = new DoctorResponseWebClientDTO();

        dto.setId(doctor.getId());
        dto.setUserId(doctor.getUser().getId());
        dto.setFullName(doctor.getFullName());
        dto.setBio(doctor.getBio());
        dto.setGender(doctor.getGender());
        dto.setAddress(doctor.getAddress());
        dto.setAvatarUrl(doctor.getAvatarUrl());

        return dto;
    }

    public Optional<Doctor> getDoctorByIdForAppointment(UUID id) {
        return doctorRepository.findById(id);
    }
}
