package com.healthpro.authservice.kafka;

import com.healthpro.authservice.dto.ClinicDto;
import com.healthpro.authservice.dto.DoctorDto;
import com.healthpro.authservice.entity.Clinic;
import com.healthpro.authservice.entity.Doctor;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@AllArgsConstructor
@Slf4j
public class MessageProducer {

    private final WebClient webClient;

    public void creatDoctor(Doctor doctor){
        DoctorDto doctorDto = DoctorDto.builder()
                .id(doctor.getId())
                .userId(doctor.getUser().getId())
                .address(doctor.getAddress())
                .gender(doctor.getGender())
                .bio(doctor.getBio())
                .fullName(doctor.getFullName())
                .avatarUrl(doctor.getAvatarUrl())
                .build();
        webClient.post()
                .uri("http://clinic-service:8081/api/v2/doctors")
                .bodyValue(doctorDto)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(aVoid -> log.info("Doctor created successfully in clinic-service for doctor id: {}", doctor.getId()))
                .doOnError(error -> log.error("Failed to create doctor in clinic-service for doctor id: {}. Error: {}", doctor.getId(), error.getMessage()))
                .subscribe();
    }

    public void creatClinic(Clinic clinic){
        ClinicDto clinicDto = ClinicDto.builder()
                .id(clinic.getId())
                .userId(clinic.getUser().getId())
                .clinicName(clinic.getClinicName())
                .weekdayOpenHour(clinic.getWeekdayOpenHour())
                .weekdayCloseHour(clinic.getWeekdayCloseHour())
                .weekendOpenHour(clinic.getWeekendOpenHour())
                .weekendCloseHour(clinic.getWeekendCloseHour())
                .logoUrl(clinic.getLogoUrl())
                .address(clinic.getAddress())
                .avatarUrl(clinic.getAvatarUrl())
                .description(clinic.getDescription())
                .build();
        webClient.post()
                .uri("http://clinic-service:8081/api/v2/clinics")
                .bodyValue(clinicDto)
                .retrieve()
                .bodyToMono(Void.class)
                .doOnSuccess(aVoid -> log.info("Clinic created successfully in clinic-service for clinic id: {}", clinic.getId()))
                .doOnError(error -> log.error("Failed to create clinic in clinic-service for clinic id: {}. Error: {}", clinic.getId(), error.getMessage()))
                .subscribe();
    }

}
