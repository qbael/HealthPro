package com.healthpro.clinicservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ApiResponseDto<T> {
    private int status;
    private boolean success;
    private String message;
    private T data;
    private LocalDateTime timestamp;

    public ApiResponseDto(int status, boolean success, String message, T data) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.data = data;
        this.timestamp = LocalDateTime.now();
    }

    public static <T> ApiResponseDto<T> success(T data, String message) {
        return new ApiResponseDto<>(200, true, message, data);
    }

    public static <T> ApiResponseDto<T> error(int status, String message) {
        return new ApiResponseDto<>(status, false, message, null);
    }

}
