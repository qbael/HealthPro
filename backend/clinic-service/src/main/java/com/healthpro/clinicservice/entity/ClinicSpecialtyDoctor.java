package com.healthpro.clinicservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.UUID;

@Builder
@Entity
@Table(name = "clinic_specialty_doctors", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"clinic_specialty_id", "doctor_id"})
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClinicSpecialtyDoctor {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_specialty_id", nullable = false)
    private ClinicSpecialty clinicSpecialty;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doctor_id", nullable = false)
    private Doctor doctor;
}