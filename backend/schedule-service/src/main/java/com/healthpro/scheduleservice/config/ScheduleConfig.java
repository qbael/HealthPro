package com.healthpro.scheduleservice.config;

import com.healthpro.scheduleservice.service.ScheduleGenerationService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {

    private final ScheduleGenerationService scheduleGenerationService;

    public ScheduleConfig(ScheduleGenerationService scheduleGenerationService) {
        this.scheduleGenerationService = scheduleGenerationService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Ho_Chi_Minh")
    @Async
    public void scheduleDailySlotGeneration() {
        scheduleGenerationService.generateSlotDaily();
    }

    @Bean
    public ApplicationRunner runOnStartup() {
        return args -> {
            scheduleGenerationService.initializeSlotData();
        };
    }
}
