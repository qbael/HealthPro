package com.healthpro.authservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class ClinicTimeDTO {
    private String fromTime;
    private String toTime;
}
