package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.repository.DoctorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<Page<Doctor>> getDoctors(Pageable pageable) {
        return Optional.of(doctorRepository.findAll(pageable));
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(UUID.fromString(id));
    }
}
