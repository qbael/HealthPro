package com.healthpro.scheduleservice.controller;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyDoctorDto;
import com.healthpro.scheduleservice.service.ClinicSpecialtyDoctorsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/v3/clinic-specialty-doctors")
@AllArgsConstructor
public class ClinicSpecialtyDoctorsController {
    private final ClinicSpecialtyDoctorsService clinicSpecialtyDoctorsService;

    @PostMapping
    public ResponseEntity<Void> addDoctorToClinicSpecialty(@RequestBody ClinicSpecialtyDoctorDto clinicSpecialtyDoctorDto) {
        try {
            System.out.println("Adding doctor to clinic specialty: " + clinicSpecialtyDoctorDto.getDoctorId());
            clinicSpecialtyDoctorsService.addedDoctorToClinicSpecialty(clinicSpecialtyDoctorDto);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error adding doctor to clinic specialty: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> removeDoctorFromClinicSpecialty(@RequestBody ClinicSpecialtyDoctorDto clinicSpecialtyDoctorDto) {
        try {
            clinicSpecialtyDoctorsService.removeDoctorFromClinicSpecialty(
                    clinicSpecialtyDoctorDto.getClinicSpecialtyId(),
                    clinicSpecialtyDoctorDto.getDoctorId()
            );
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.err.println("Error removing doctor from clinic specialty: " + e.getMessage());
            return ResponseEntity.status(500).build();
        }
    }
}
