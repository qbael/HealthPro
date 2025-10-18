package com.healthpro.scheduleservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
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
    @Column(name = "clinic_specialty_id")
    private UUID clinicSpecialtyId;

    @NotNull
    @Column(name = "doctor_id")
    private UUID doctorId;

    @Min(value = 0, message = "Số lượng được phân công không thể âm")
    @Column(name = "assignment_count")
    private Integer assignmentCount = 0;
}