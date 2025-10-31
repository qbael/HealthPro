package com.healthpro.clinicservice.service;

import com.healthpro.clinicservice.entity.ClinicInvitation;
import com.healthpro.clinicservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.clinicservice.repository.ClinicSpecialtyDoctorRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ClinicSpecialtyDoctorService {
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;

    public void createClinicSpecialtyDoctor(ClinicInvitation clinicInvitation) {
        ClinicSpecialtyDoctor clinicSpecialtyDoctor = new ClinicSpecialtyDoctor();
        clinicSpecialtyDoctor.setClinicSpecialty(clinicInvitation.getClinicSpecialty());
        clinicSpecialtyDoctor.setDoctor(clinicInvitation.getDoctor());
        clinicSpecialtyDoctorRepository.save(clinicSpecialtyDoctor);
    }
}
