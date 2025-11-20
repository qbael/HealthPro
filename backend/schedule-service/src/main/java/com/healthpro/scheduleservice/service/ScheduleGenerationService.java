package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyScheduleTemplateRepository;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Slf4j
@Service
public class ScheduleGenerationService {

    public static final int DEFAULT_DAYS_AHEAD = 14;
    private final DoctorAvailableSlotService doctorAvailableSlotService;
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final ClinicSpecialtyScheduleTemplateRepository clinicSpecialtyScheduleTemplateRepository;

    public ScheduleGenerationService(DoctorAvailableSlotService doctorAvailableSlotService,
                                     DoctorScheduleTemplateRepository doctorScheduleTemplateRepository,
                                     ClinicSpecialtyScheduleTemplateRepository clinicSpecialtyScheduleTemplateRepository) {
        this.doctorAvailableSlotService = doctorAvailableSlotService;
        this.doctorScheduleTemplateRepository = doctorScheduleTemplateRepository;
        this.clinicSpecialtyScheduleTemplateRepository = clinicSpecialtyScheduleTemplateRepository;
    }

    public void initializeSlotData(){
        if (doctorAvailableSlotService.countSlots() == 0) {
            generateSlotFromDoctorTemplate();
            generateSlotFromClinicSpecialtyTemplate();
        } else {
            generateSlotDaily();
        }
    }

    public void generateSlotFromDoctorTemplate() {
        int page = 0;
        int pageSize = 5;
        Page<DoctorScheduleTemplate> doctorScheduleTemplates;

        do {
            Pageable pageable = PageRequest.of(page, pageSize);
            doctorScheduleTemplates = doctorScheduleTemplateRepository.findAll(pageable);
            for (DoctorScheduleTemplate template : doctorScheduleTemplates.getContent()) {
                try {
                    doctorAvailableSlotService.generateSlots(template);
                    log.info("Đã tạo khung giờ hẹn cho bác sĩ với ID {}", template.getDoctorId());
                } catch (RuntimeException e) {
                    log.error("Lỗi khi tạo khung giờ hẹn cho bác sĩ với ID {}: {}", template.getDoctorId(), e.getMessage());
                }
            }
            page++;
        } while (doctorScheduleTemplates.hasNext());
    }

    public void generateSlotFromClinicSpecialtyTemplate() {
        int page = 0;
        int pageSize = 5;
        Page<ClinicSpecialtyScheduleTemplate> clinicSpecialtyTemplates;

        do {
            Pageable pageable = PageRequest.of(page, pageSize);
            clinicSpecialtyTemplates = clinicSpecialtyScheduleTemplateRepository.findAll(pageable);
            for (ClinicSpecialtyScheduleTemplate template : clinicSpecialtyTemplates.getContent()) {
                try {
                    doctorAvailableSlotService.generateSlots(template);
                    log.info("Đã tạo khung giờ hẹn cho chuyên khoa phòng khám với ID {}", template.getClinicSpecialtyId());
                } catch (RuntimeException e) {
                    log.error("Lỗi khi tạo khung giờ hẹn cho chuyên khoa phòng khám với ID {}: {}", template.getClinicSpecialtyId(), e.getMessage());
                }
            }
            page++;
        } while (clinicSpecialtyTemplates.hasNext());
    }

    @Async
    public void generateSlotDaily(){
        doctorAvailableSlotService.deleteSlotBeforeToday();
        generateSlotsDailyForDoctors();
        generateSlotsDailyForClinicSpecialties();
    }

    @Async
    public void generateSlotsDailyForDoctors(){
        LocalDate dateToGenerate = LocalDate.now().plusDays(1).plusDays(DEFAULT_DAYS_AHEAD);
        if (doctorAvailableSlotService.getMaxDate().isEqual(dateToGenerate)) {
            return;
        }
        DayOfWeek dayOfWeek = dateToGenerate.getDayOfWeek();
        List<DoctorScheduleTemplate> doctorTemplates = doctorScheduleTemplateRepository.findByDayOfWeek(dayOfWeek);
        for (DoctorScheduleTemplate template : doctorTemplates) {
            try {
                doctorAvailableSlotService.generateSlots(template, dateToGenerate);
                log.info("Đã tạo khung giờ hẹn ngày mới cho bác sĩ với ID {}", template.getDoctorId());
            } catch (RuntimeException e) {
                log.error("Lỗi khi tạo khung giờ hẹn ngày mới cho bác sĩ với ID {}: {}", template.getDoctorId(), e.getMessage());
            }
        }
    }

    @Async
    public void generateSlotsDailyForClinicSpecialties(){
        LocalDate dateToGenerate = LocalDate.now().plusDays(1).plusDays(DEFAULT_DAYS_AHEAD);
        if (doctorAvailableSlotService.getMaxDate().isEqual(dateToGenerate)) {
            return;
        }
        DayOfWeek dayOfWeek = dateToGenerate.getDayOfWeek();
        List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyTemplates = clinicSpecialtyScheduleTemplateRepository.findByDayOfWeek(dayOfWeek);
        for (ClinicSpecialtyScheduleTemplate template : clinicSpecialtyTemplates) {
            try {
                doctorAvailableSlotService.generateSlots(template, dateToGenerate);
                log.info("Đã tạo khung giờ hẹn ngày mới cho chuyên khoa phòng khám với ID {}", template.getClinicSpecialtyId());
            } catch (RuntimeException e) {
                log.error("Lỗi khi tạo khung giờ hẹn ngày mới cho chuyên khoa phòng khám với ID {}: {}", template.getClinicSpecialtyId(), e.getMessage());
            }
        }
    }
}
