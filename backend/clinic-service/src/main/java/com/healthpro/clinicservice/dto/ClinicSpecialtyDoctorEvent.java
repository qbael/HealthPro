package com.healthpro.clinicservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ClinicSpecialtyDoctorEvent {
    private UUID id;
    private UUID clinicSpecialtyId;
    private UUID doctorId;
    private EventType eventType;

    public enum EventType {
        CREATED,
        UPDATED,
        DELETED
    }
}