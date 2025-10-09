package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.Specialty;
import com.healthpro.clinicservice.repository.SpecialtyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SpecialtyService {

    private final SpecialtyRepository specialtyRepository;

    public SpecialtyService(SpecialtyRepository specialtyRepository) {
        this.specialtyRepository = specialtyRepository;
    }

    public List<Specialty> getSpecialties() {
        return specialtyRepository.findAll();
    }

}
