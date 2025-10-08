package com.healthpro.authservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.validator.constraints.URL;

import java.util.UUID;

@Entity
@Table(name = "clinics")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Clinic {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User không được để trống")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Tên phòng khám không được để trống")
    @Size(max = 255, message = "Tên phòng khám không được vượt quá 255 ký tự")
    @Column(name = "clinic_name", nullable = false)
    private String clinicName;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    @Column(name = "address")
    private String address;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ mở cửa phải theo định dạng HH:mm")
    @Column(name = "open_hour", length = 5)
    private String openHour = "08:00";

    @Pattern(regexp = "^([0-1][0-9]|2[0-3]):[0-5][0-9]$", message = "Giờ đóng cửa phải theo định dạng HH:mm")
    @Column(name = "close_hour", length = 5)
    private String closeHour = "17:00";

    @URL(message = "URL không hợp lệ")
    @Column(name = "logo_url")
    private String logoUrl;
}
