package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.DoctorDto;
import com.healthpro.clinicservice.entity.Doctor;
import com.healthpro.clinicservice.entity.DoctorSpecialty;
import com.healthpro.clinicservice.repository.DoctorRepository;
import com.healthpro.clinicservice.repository.DoctorSpecialtyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorSpecialtyRepository doctorSpecialtyRepository;

    public DoctorService(DoctorRepository doctorRepository,
                         DoctorSpecialtyRepository doctorSpecialtyRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorSpecialtyRepository = doctorSpecialtyRepository;
    }

    public Optional<Page<Doctor>> getDoctors(Pageable pageable) {
        return Optional.of(doctorRepository.findAll(pageable));
    }

    public Optional<Doctor> getDoctorById(String id) {
        return doctorRepository.findById(UUID.fromString(id));
    }

    public Optional<List<DoctorSpecialty>> getSpecialtiesByDoctorId(String id) {
        return Optional.ofNullable(doctorSpecialtyRepository.findAllByDoctor_Id(UUID.fromString(id)));
    }

    public Optional<Doctor> createDoctor(DoctorDto doctorRequest) {
        Doctor doctor = Doctor.builder()
                .id(doctorRequest.getId())
                .userId(doctorRequest.getUserId())
                .address(doctorRequest.getAddress())
                .gender(doctorRequest.getGender())
                .bio(doctorRequest.getBio())
                .fullName(doctorRequest.getFullName())
                .avatarUrl(doctorRequest.getAvatarUrl())
                .isInClinicSpecialty(false)
                .build();
        return Optional.of(doctorRepository.save(doctor));
    }
}
