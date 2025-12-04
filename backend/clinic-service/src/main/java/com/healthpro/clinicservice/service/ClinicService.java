package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.dto.ClinicDto;
import com.healthpro.clinicservice.dto.ClinicSpecialtyInfoResponseDto;
import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.ClinicSpecialty;
import com.healthpro.clinicservice.repository.ClinicRepository;
import com.healthpro.clinicservice.repository.ClinicSpecialtyRepository;
import jakarta.validation.Valid;
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

    public Page<Clinic> getClinics(Pageable pageable) {
        return clinicRepository.findAll(pageable);
    }

    public Optional<Clinic> getClinicById(String id) {
        return clinicRepository.findById(UUID.fromString(id));
    }

    public Optional<List<ClinicSpecialty>> getSpecialtiesByClinicId(String id) {
        return Optional.ofNullable(clinicSpecialtyRepository.findAllByClinic_Id(UUID.fromString(id)));
    }

    public Optional<ClinicSpecialty> getClinicSpecialtyById(String clinicSpecialtyId) {
        return clinicSpecialtyRepository.findById(UUID.fromString(clinicSpecialtyId));
    }

    public Optional<Clinic> createClinic(@Valid ClinicDto clinicRequest) {
        Clinic clinic = Clinic.builder()
                .id(clinicRequest.getId())
                .userId(clinicRequest.getUserId())
                .clinicName(clinicRequest.getClinicName())
                .weekdayOpenHour(clinicRequest.getWeekdayOpenHour())
                .weekdayCloseHour(clinicRequest.getWeekdayCloseHour())
                .weekendOpenHour(clinicRequest.getWeekendOpenHour())
                .weekendCloseHour(clinicRequest.getWeekendCloseHour())
                .logoUrl(clinicRequest.getLogoUrl())
                .address(clinicRequest.getAddress())
                .avatarUrl(clinicRequest.getAvatarUrl())
                .description(clinicRequest.getDescription())
                .build();
        return Optional.of(clinicRepository.save(clinic));
    }
}
