package com.healthpro.clinicservice.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clinic_specialties")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicSpecialty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "clinic_id", nullable = false)
    private Clinic clinic;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "specialty_id", nullable = false)
    private Specialty specialty;

    @OneToMany(mappedBy = "clinicSpecialty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicInvitation> invitations;

    @OneToMany(mappedBy = "clinicSpecialty", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicSpecialtyDoctor> assignedDoctors;
}
