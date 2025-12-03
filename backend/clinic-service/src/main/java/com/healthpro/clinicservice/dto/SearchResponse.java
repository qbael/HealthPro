package com.healthpro.clinicservice.dto;

import com.healthpro.clinicservice.entity.Clinic;
import com.healthpro.clinicservice.entity.Doctor;

import java.util.List;

public record SearchResponse(
        List<Doctor> doctors,
        List<Clinic> clinics
) {
}