package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.AppointmentInfoResponseDto;
import com.healthpro.authservice.entity.Clinic;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class AppointmentService {

    private final PatientService patientService;
    private final DoctorService doctorService;
    private final ClinicService clinicService;

    public AppointmentService(PatientService patientService, DoctorService doctorService, ClinicService clinicService) {
        this.patientService = patientService;
        this.doctorService = doctorService;
        this.clinicService = clinicService;
    }

    public AppointmentInfoResponseDto getAppointmentInfo(String patientId, String doctorId, String clinicId) {
        Optional<Patient> patientOpt = patientService.getPatientById(UUID.fromString(patientId));
        if (patientOpt.isEmpty()) {
            throw new UserNotFoundException("Patient with user id " + patientId + " not found");
        }
        Patient patient = patientOpt.get();
        if (!doctorId.isEmpty()) {
            Optional<Doctor> doctorOpt = doctorService.getDoctorByIdForAppointment(UUID.fromString(doctorId));
            if (doctorOpt.isEmpty()) {
                throw new IllegalArgumentException("Doctor with id " + doctorId + " not found");
            }
            Doctor doctor = doctorOpt.get();
            return new AppointmentInfoResponseDto(
                    patient.getId(),
                    patient.getFullName(),
                    patient.getUser().getEmail(),
                    patient.getUser().getPhoneNumber(),
                    doctor.getId(),
                    doctor.getFullName(),
                    null,
                    null,
                    doctor.getAddress(),
                    doctor.getUser().getPhoneNumber()
            );
        } else if (!clinicId.isEmpty()) {
            Optional<Clinic> clinicOpt = clinicService.getClinicByIdForAppointment(UUID.fromString(clinicId));
            if (clinicOpt.isEmpty()) {
                throw new IllegalArgumentException("Clinic with id " + clinicId + " not found");
            }
            var clinic = clinicOpt.get();
            return new AppointmentInfoResponseDto(
                    patient.getId(),
                    patient.getFullName(),
                    patient.getUser().getEmail(),
                    patient.getUser().getPhoneNumber(),
                    null,
                    null,
                    clinic.getId(),
                    clinic.getClinicName(),
                    clinic.getAddress(),
                    clinic.getUser().getPhoneNumber()
            );
        } else {
            throw new IllegalArgumentException("Either doctorId or clinicId must be provided");
        }
    }
}
