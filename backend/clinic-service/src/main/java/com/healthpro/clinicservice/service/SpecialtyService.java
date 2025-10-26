package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.Specialty;
import com.healthpro.clinicservice.exception.ResourceNotFoundException;
import com.healthpro.clinicservice.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> getAllSpecialties() {
        return specialtyRepository.findAll();
    }

    public Specialty getSpecialtyById(UUID id) {
        return specialtyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Not found specialty"));
    }
}
