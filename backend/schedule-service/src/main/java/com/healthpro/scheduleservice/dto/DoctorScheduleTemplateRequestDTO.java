package com.healthpro.scheduleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.UUID;

@Getter
@Setter
public class DoctorScheduleTemplateRequestDTO {
    private UUID doctorId;
    private DayOfWeek[] dayOfWeek;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime fromTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime toTime;
    private Integer slotDuration;
}
