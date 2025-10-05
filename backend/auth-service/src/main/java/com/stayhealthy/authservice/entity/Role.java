package com.stayhealthy.authservice.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
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
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotBlank(message = "Role name không được để trống")
    @Size(max = 20, message = "Role name không được vượt quá 20 ký tự")
    @Column(name = "role_name", length = 20, nullable = false)
    private String roleName;

    @OneToMany(mappedBy = "role")
    @Builder.Default
    @JsonBackReference
    private Set<User> users = new HashSet<>();
}
