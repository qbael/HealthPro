package com.healthpro.scheduleservice.config;

import jakarta.annotation.PostConstruct;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.TimeZone;

@Configuration
@EnableAsync
@EnableScheduling
public class GlobalConfig {
    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("Asia/Ho_Chi_Minh"));
    }
}
