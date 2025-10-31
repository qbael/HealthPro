package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicResponseDTO;
import com.healthpro.clinicservice.dto.ClinicSpecialtyRequestDTO;
import com.healthpro.clinicservice.dto.ClinicSpecialtyResponseDTO;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.entity.Specialty;
import com.healthpro.clinicservice.exception.ResourceNotFoundException;
import com.healthpro.clinicservice.mapper.ClinicSpecialtyDTOMapper;
import com.healthpro.clinicservice.mapper.DoctorSpecialtyDTOMapper;
import com.healthpro.clinicservice.repository.ClinicRepository;
import com.healthpro.clinicservice.repository.ClinicSpecialtyRepository;
import com.healthpro.clinicservice.repository.SpecialtyRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ClinicSpecialtyService {
    private final ClinicSpecialtyRepository clinicSpecialtyRepository;
    private final SpecialtyRepository specialtyRepository;
    private final ClinicRepository clinicRepository;
    private final WebClient webClient;

    public List<ClinicSpecialtyResponseDTO> getAllClinicSpecialties(UUID clinicId) {
        List<ClinicSpecialty> clinicSpecialty = clinicSpecialtyRepository.findAllByClinic_Id(clinicId);

        return clinicSpecialty.stream().map(ClinicSpecialtyDTOMapper::toDTO).toList();
    }

    @Transactional
    public void createClinicSpecialty(
            UUID clinicId, ClinicSpecialtyRequestDTO clinicSpecialtyRequestDTO
    ) {
        clinicSpecialtyRepository.deleteByClinicId(clinicId);

        ClinicResponseDTO clinicDTO = webClient.get()
                .uri("http://localhost:8080/api/v1/clinics/{id}", clinicId)
                .retrieve()
                .bodyToMono(ClinicResponseDTO.class)
                .block();

        if (clinicDTO == null) {
            throw new ResourceNotFoundException("Clinic not found");
        }

        Clinic clinic = clinicRepository.findById(clinicId)
                .orElseGet(() -> {
                    Clinic newClinic = Clinic.builder()
                            .id(clinicId)
                            .userId(clinicDTO.getUserId())
                            .clinicName(clinicDTO.getClinicName())
                            .address(clinicDTO.getAddress())
                            .description(clinicDTO.getDescription())
                            .weekdayOpenHour(clinicDTO.getWeekdayOpenHour())
                            .weekdayCloseHour(clinicDTO.getWeekdayCloseHour())
                            .weekendOpenHour(clinicDTO.getWeekendOpenHour())
                            .weekendCloseHour(clinicDTO.getWeekendCloseHour())
                            .logoUrl(clinicDTO.getLogoUrl())
                            .avatarUrl(clinicDTO.getAvatarUrl())
                            .build();
                    return clinicRepository.save(newClinic);
                });

        for (UUID specialtyId : clinicSpecialtyRequestDTO.getSpecialty()) {
            Specialty specialty = specialtyRepository.findById(specialtyId)
                    .orElseThrow(() -> new ResourceNotFoundException("specialty not found"));

            ClinicSpecialty clinicSpecialty = new ClinicSpecialty();
            clinicSpecialty.setClinic(clinic);
            clinicSpecialty.setSpecialty(specialty);

            clinicSpecialtyRepository.save(clinicSpecialty);
        }
    }
}
