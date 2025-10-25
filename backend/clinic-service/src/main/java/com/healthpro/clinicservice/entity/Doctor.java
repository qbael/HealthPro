package com.healthpro.clinicservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Doctor {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    @NotNull(message = "Id người dùng không được ")
    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    @Size(min = 2, max = 255, message = "Họ tên phải dưới 255 kí tự")
    @Column(name = "full_name")
    private String fullName;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "gender")
    private Boolean gender;

    @Size(max = 255, message = "Họ tên phải dưới 255 kí tự")
    @Column(name = "address")
    private String address;

    @URL(message = "URL không hợp lệ")
    @Size(max = 255)
    @Column(name = "avatar_url")
    private String avatarUrl;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DoctorSpecialty> doctorSpecialties;

    @OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private Set<ClinicInvitation> invitations;
}
