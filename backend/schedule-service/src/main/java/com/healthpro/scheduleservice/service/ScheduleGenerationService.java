package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyScheduleTemplateRepository;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ScheduleGenerationService {

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

    public void generateSlotFromDoctorTemplate() {
        int page = 0;
        int pageSize = 5;
        Page<DoctorScheduleTemplate> doctorScheduleTemplates;

        do {
            Pageable pageable = PageRequest.of(page, pageSize);
            doctorScheduleTemplates = doctorScheduleTemplateRepository.findByIsActiveTrue(pageable);
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
            clinicSpecialtyTemplates = clinicSpecialtyScheduleTemplateRepository.findByIsActiveTrue(pageable);
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
}
