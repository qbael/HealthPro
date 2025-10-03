package com.stayhealthy.authservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity
@Table(name = "doctors")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Doctor {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "User không được để trống")
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @NotBlank(message = "Họ tên không được để trống")
    @Size(max = 255, message = "Họ tên không được vượt quá 255 ký tự")
    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "gender")
    private Boolean gender;

    @Size(max = 255, message = "Địa chỉ không được vượt quá 255 ký tự")
    @Column(name = "address", length = 255)
    private String address;

    @Column(name = "avatar_url")
    private String avatarUrl;
}
