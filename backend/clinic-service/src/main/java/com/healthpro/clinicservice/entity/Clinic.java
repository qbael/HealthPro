package com.healthpro.clinicservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "Id người dùng không được trống")
    @Column(name = "user_id", unique = true, nullable = false)
    private UUID userId;

    @NotBlank(message = "Tên phòng khám không được trống")
    @Size(max = 255, message = "Tên phòng khám phải dưới 255 kí tự")
    @Column(name = "clinic_name", nullable = false)
    private String clinicName;

    @Size(max = 255, message = "Địa chỉ phải dưới 255 kí tự")
    @Column(name = "address")
    private String address;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Định dạng thời gian không hợp lệ")
    @Column(name = "open_hour", length = 5)
    private String openHour;

    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):[0-5][0-9]$", message = "Định dạng thời gian không hợp lệ")
    @Column(name = "close_hour", length = 5)
    private String closeHour;

    @URL(message = "URL không hợp lệ")
    @Column(name = "logo_url")
    private String logoUrl;

    @OneToMany(mappedBy = "clinic", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ClinicSpecialty> clinicSpecialties;
}