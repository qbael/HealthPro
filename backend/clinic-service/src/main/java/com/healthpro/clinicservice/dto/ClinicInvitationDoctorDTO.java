package com.healthpro.clinicservice.dto;

import com.healthpro.clinicservice.entity.enums.InvitationStatus;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class ClinicInvitationDoctorDTO {
    private UUID id;
    private InvitationStatus status;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;

    private UUID clinicSpecialtyId;
    private UUID specialtyId;
    private String specialtyName;

    private UUID doctorId;
    private String fullName;
    private String bio;
    private Boolean gender;
    private String address;
    private String avatarUrl;
}
