package com.healthpro.scheduleservice.exception;

public class ScheduleNotFoundException extends RuntimeException {
    public ScheduleNotFoundException(String message) {
        super(message);
    }
}
