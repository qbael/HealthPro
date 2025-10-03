package com.stayhealthy.authservice.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Role {

    @Id
    @GeneratedValue(generator = "UUID", strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Role name không được để trống")
    @Size(max = 20, message = "Role name không được vượt quá 20 ký tự")
    @Column(name = "role_name", length = 20, nullable = false)
    private String roleName;
}
