package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.DoctorScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.mapper.DoctorScheduleTemplateMapper;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class DoctorScheduleTemplateService {
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;
    private final DoctorAvailableSlotService doctorAvailableSlotService;
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;
    private final ClinicSpecialtyScheduleTemplateService clinicSpecialtyScheduleTemplateService;

    public List<DoctorScheduleTemplateDTO> getAllDoctorScheduleTemplates(UUID doctorId) {
        List<DoctorScheduleTemplate> doctorScheduleTemplates = doctorScheduleTemplateRepository.findByDoctorId(doctorId);

        if (doctorScheduleTemplates.isEmpty()) {
            return List.of();
        }

        return DoctorScheduleTemplateMapper.toDoctorScheduleTemplateResponseDTO(doctorScheduleTemplates);
    }

    @Transactional
    public void createDoctorScheduleTemplate(UUID doctorId, List<DoctorScheduleTemplateDTO> newTemplates) {
        List<DoctorScheduleTemplate> existingTemplates = doctorScheduleTemplateRepository.findByDoctorId(doctorId);

        Map<DayOfWeek, DoctorScheduleTemplate> existingMap = existingTemplates.stream()
                .collect(Collectors.toMap(DoctorScheduleTemplate::getDayOfWeek, t -> t));

        List<DoctorScheduleTemplate> toKeep = new ArrayList<>();
        List<DoctorScheduleTemplateDTO> toCreate = new ArrayList<>();

        for (DoctorScheduleTemplateDTO dto : newTemplates) {
            DoctorScheduleTemplate existing = existingMap.get(dto.getDayOfWeek());
            if (existing != null &&
                    existing.getFromTime().equals(dto.getFromTime()) &&
                    existing.getToTime().equals(dto.getToTime()) &&
                    existing.getSlotDuration().equals(dto.getSlotDuration())) {
                toKeep.add(existing);
            } else {
                toCreate.add(dto);
            }
        }

        List<DoctorScheduleTemplate> toDelete = existingTemplates.stream()
                .filter(t -> toKeep.stream().noneMatch(k -> k.getDayOfWeek() == t.getDayOfWeek()))
                .toList();

        if (!toDelete.isEmpty()) {
            List<UUID> templateIds = toDelete.stream().map(DoctorScheduleTemplate::getId).toList();
            doctorAvailableSlotService.deleteAvailableSlotsByTemplateIdIn(templateIds);
            doctorScheduleTemplateRepository.deleteAll(toDelete);
        }

        for (DoctorScheduleTemplateDTO dto : toCreate) {
            DoctorScheduleTemplate entity = new DoctorScheduleTemplate();
            entity.setDoctorId(doctorId);
            entity.setDayOfWeek(dto.getDayOfWeek());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());
            entity.setSlotDuration(dto.getSlotDuration());
            doctorScheduleTemplateRepository.save(entity);
            doctorAvailableSlotService.generateSlots(entity);
        }

        Optional<ClinicSpecialtyDoctor> clinicSpecialtyDoctorOpt =
                clinicSpecialtyDoctorRepository.findByDoctorId(doctorId);
        clinicSpecialtyDoctorOpt.ifPresent(clinicSpecialtyDoctor ->
                clinicSpecialtyScheduleTemplateService.refixDoctorScheduleTemplatesInClinicSpecialty(
                        clinicSpecialtyDoctor.getClinicSpecialtyId(),
                        doctorId
                )
        );
    }

    public void deleteAll() {
        doctorScheduleTemplateRepository.deleteAll();
    }
}
