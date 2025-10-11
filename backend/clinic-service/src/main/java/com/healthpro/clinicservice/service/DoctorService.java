package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.repository.DoctorRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    public Optional<List<Doctor>> getDoctors(Integer maxResults) {
        return Optional.of(doctorRepository.findAll().stream().limit(maxResults).toList());
    }
}
