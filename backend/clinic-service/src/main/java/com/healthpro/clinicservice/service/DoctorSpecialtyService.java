package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicResponseDTO;
import com.healthpro.clinicservice.dto.ClinicSpecialtyRequestDTO;
import com.healthpro.clinicservice.dto.DoctorResponseDTO;
import com.healthpro.clinicservice.dto.DoctorSpecialtyRequestDTO;
import com.healthpro.clinicservice.entity.*;
import com.healthpro.clinicservice.exception.ResourceNotFoundException;
import com.healthpro.clinicservice.repository.*;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class DoctorSpecialtyService {
    private final DoctorSpecialtyRepository doctorSpecialtyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final DoctorRepository doctorRepository;
    private final WebClient webClient;

    public List<DoctorSpecialty> getAllDoctorSpecialties(UUID doctorId) {
        return doctorSpecialtyRepository.findAllByDoctor_Id(doctorId);
    }

    @Transactional
    public void createDoctorSpecialty(
            UUID doctorId, DoctorSpecialtyRequestDTO doctorSpecialtyRequestDTO
    ) {
        DoctorResponseDTO doctorDTO = webClient.get()
                .uri("http://localhost:8080/api/v1/doctors/{id}", doctorId)
                .retrieve()
                .bodyToMono(DoctorResponseDTO.class)
                .block();

        if (doctorDTO == null) {
            throw new ResourceNotFoundException("Doctor not found");
        }

        Doctor doctor = doctorRepository.findById(doctorId)
                .orElseGet(() -> {
                    Doctor newDoctor = Doctor.builder()
                            .id(doctorId)
                            .userId(doctorDTO.getUserId())
                            .fullName(doctorDTO.getFullName())
                            .bio(doctorDTO.getBio())
                            .gender(doctorDTO.getGender())
                            .address(doctorDTO.getAddress())
                            .avatarUrl(doctorDTO.getAvatarUrl())
                            .build();
                    return doctorRepository.save(newDoctor);
                });

        for (UUID specialtyId : doctorSpecialtyRequestDTO.getSpecialty()) {
            Specialty specialty = specialtyRepository.findById(specialtyId)
                    .orElseThrow(() -> new ResourceNotFoundException("specialty not found"));

            DoctorSpecialty doctorSpecialty = new DoctorSpecialty();
            doctorSpecialty.setDoctor(doctor);
            doctorSpecialty.setSpecialty(specialty);

            doctorSpecialtyRepository.save(doctorSpecialty);
        }
    }
}
