package com.healthpro.clinicservice.dto;

import com.healthpro.clinicservice.entity.enums.InvitationStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public class ClinicInvitationResponseDTO {
    private UUID id;
    private InvitationStatus status;
    private LocalDateTime invitedAt;
    private LocalDateTime respondedAt;
    private String title;

    private UUID clinicSpecialtyId;
    private UUID specialtyId;
    private String specialtyName;

    private UUID clinicId;
    private String clinicName;
    private String address;
    private String description;
    private String weekdayOpenHour;
    private String weekdayCloseHour;
    private String weekendOpenHour;
    private String weekendCloseHour;
    private String logoUrl;
    private String avatarUrl;
}
