package com.healthpro.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DoctorResponseWebClientDTO {
    private UUID id;
    private UUID userId;
    private String fullName;
    private String bio;
    private Boolean gender;
    private String address;
    private String avatarUrl;
}
