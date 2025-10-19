package com.healthpro.scheduleservice.config;

import com.healthpro.scheduleservice.service.DoctorAvailableSlotService;
import com.healthpro.scheduleservice.service.ScheduleGenerationService;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
public class ScheduleConfig {

    private final ScheduleGenerationService scheduleGenerationService;
    private final DoctorAvailableSlotService doctorAvailableSlotService;

    public ScheduleConfig(ScheduleGenerationService scheduleGenerationService,
                          DoctorAvailableSlotService doctorAvailableSlotService) {
        this.scheduleGenerationService = scheduleGenerationService;
        this.doctorAvailableSlotService = doctorAvailableSlotService;
    }

    @Scheduled(cron = "0 0 0 * * *", zone = "Asia/Ho_Chi_Minh")
    @Async
    public void scheduleDailySlotGeneration() {
        doctorAvailableSlotService.deleteSlotBeforeToday();
        scheduleGenerationService.generateSlotFromDoctorTemplate();
        scheduleGenerationService.generateSlotFromClinicSpecialtyTemplate();
    }

    @Bean
    public ApplicationRunner runOnStartup() {
        return args -> {
            doctorAvailableSlotService.deleteSlotBeforeToday();
            scheduleGenerationService.generateSlotFromDoctorTemplate();
            scheduleGenerationService.generateSlotFromClinicSpecialtyTemplate();
        };
    }
}
