package com.healthpro.clinicservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "specialties")
@Getter
@Setter
public class Specialty {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Tên chuyên khoa không thể trống")
    @Column(name = "name", unique = true, nullable = false)
    private String name;
}