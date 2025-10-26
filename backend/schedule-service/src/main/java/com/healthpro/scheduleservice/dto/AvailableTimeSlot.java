package com.healthpro.scheduleservice.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record AvailableTimeSlot (LocalDate appointmentDate, LocalTime startTime, LocalTime endTime) {
}
