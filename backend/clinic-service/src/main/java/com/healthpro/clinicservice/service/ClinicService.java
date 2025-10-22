package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.repository.ClinicRepository;
import com.healthpro.clinicservice.repository.ClinicSpecialtyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClinicService {
    public final ClinicRepository clinicRepository;
    public final ClinicSpecialtyRepository clinicSpecialtyRepository;

    public ClinicService(ClinicRepository clinicRepository,
                         ClinicSpecialtyRepository clinicSpecialtyRepository) {
        this.clinicRepository = clinicRepository;
        this.clinicSpecialtyRepository = clinicSpecialtyRepository;
    }

    public Clinic createClinic(Clinic clinic) {
        return clinicRepository.save(clinic);
    }

    public Optional<Page<Clinic>> getClinics(Pageable pageable) {
        return Optional.of(clinicRepository.findAll(pageable));
    }

    public Optional<Clinic> getClinicById(String id) {
        return clinicRepository.findById(UUID.fromString(id));
    }

    public Optional<List<ClinicSpecialty>> getSpecialtiesByClinicId(String id) {
        return Optional.ofNullable(clinicSpecialtyRepository.findAllByClinic_Id(UUID.fromString(id)));
    }
}
