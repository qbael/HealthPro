package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ApiResponseDto;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.repository.ClinicRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClinicService {
    public final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    public Optional<Page<Clinic>> getClinics(Pageable pageable) {
        return Optional.of(clinicRepository.findAll(pageable));
    }

    public Optional<Clinic> getClinicById(String id) {
        return clinicRepository.findById(UUID.fromString(id));
    }
}
