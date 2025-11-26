package com.healthpro.scheduleservice.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

@Getter
@Setter
public class ClinicSpecialtyScheduleTemplateDTO {
    private DayOfWeek dayOfWeek;

    @NotNull(message = "Giờ bắt đầu không được để trống")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime fromTime;

    @NotNull(message = "Giờ kết thúc không được để trống")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime toTime;

    @NotNull(message = "Thời lượng khung giờ không được để trống")
    private Integer slotDuration;

    @AssertTrue(message = "Giờ bắt đầu phải trước giờ kết thúc và khung giờ phải chia hết cho tổng thời gian")
    private boolean isValidTime() {
        if (fromTime == null || toTime == null || slotDuration == null) return true;

        if (!fromTime.isBefore(toTime)) return false;

        if ((fromTime.getMinute() != 0 && fromTime.getMinute() != 30) ||
                (toTime.getMinute() != 0 && toTime.getMinute() != 30)) return false;

        if (fromTime.isBefore(LocalTime.of(6, 0)) || toTime.isAfter(LocalTime.of(23, 0))) return false;

        if (slotDuration <= 0 || slotDuration % 5 != 0) return false;

        long totalMinutes = Duration.between(fromTime, toTime).toMinutes();
        return totalMinutes >= slotDuration;
    }
}
