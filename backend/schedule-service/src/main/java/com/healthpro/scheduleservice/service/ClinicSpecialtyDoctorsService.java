package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyDoctorDto;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;


@Service
@AllArgsConstructor
@Slf4j
public class ClinicSpecialtyDoctorsService {
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;
    private final ClinicSpecialtyScheduleTemplateService clinicSpecialtyScheduleTemplateService;

    @Transactional
    public void addedDoctorToClinicSpecialty(ClinicSpecialtyDoctorDto clinicSpecialtyDoctorDto) {
        ClinicSpecialtyDoctor clinicSpecialtyDoctor = ClinicSpecialtyDoctor.builder()
                .id(clinicSpecialtyDoctorDto.getId())
                .clinicSpecialtyId(clinicSpecialtyDoctorDto.getClinicSpecialtyId())
                .doctorId(clinicSpecialtyDoctorDto.getDoctorId())
                .assignmentCount(0)
                .build();
        clinicSpecialtyDoctorRepository.save(clinicSpecialtyDoctor);
        clinicSpecialtyScheduleTemplateService.addedDoctor(
                clinicSpecialtyDoctorDto.getClinicSpecialtyId(),
                clinicSpecialtyDoctorDto.getDoctorId()
        );
        log.info("Đã thêm bác sĩ với ID {} vào chuyên khoa phòng khám với ID {}",
                clinicSpecialtyDoctorDto.getDoctorId(),
                clinicSpecialtyDoctorDto.getClinicSpecialtyId());
    }

    @Transactional
    public void removeDoctorFromClinicSpecialty(UUID clinicSpecialtyId, UUID doctorId) {
        clinicSpecialtyDoctorRepository.deleteByClinicSpecialtyIdAndDoctorId(clinicSpecialtyId, doctorId);
        clinicSpecialtyScheduleTemplateService.removeDoctor(
                clinicSpecialtyId,
                doctorId
        );
        log.info("Đã xóa bác sĩ với ID {} khỏi chuyên khoa phòng khám với ID {}",
                doctorId,
                clinicSpecialtyId);
    }

}
