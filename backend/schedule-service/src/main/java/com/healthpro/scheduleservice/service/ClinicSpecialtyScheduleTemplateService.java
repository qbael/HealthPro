package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.ClinicSpecialtyScheduleTemplateDTO;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyDoctor;
import com.healthpro.scheduleservice.entity.ClinicSpecialtyScheduleTemplate;
import com.healthpro.scheduleservice.entity.DoctorScheduleTemplate;
import com.healthpro.scheduleservice.mapper.ClinicSpecialtyScheduleTemplateMapper;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyDoctorRepository;
import com.healthpro.scheduleservice.repository.ClinicSpecialtyScheduleTemplateRepository;
import com.healthpro.scheduleservice.repository.DoctorScheduleTemplateRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ClinicSpecialtyScheduleTemplateService {
    private final ClinicSpecialtyScheduleTemplateRepository clinicSpecialtyScheduleTemplateRepository;
    private final DoctorAvailableSlotService doctorAvailableSlotService;
    private final ClinicSpecialtyDoctorRepository clinicSpecialtyDoctorRepository;
    private final DoctorScheduleTemplateRepository doctorScheduleTemplateRepository;

    public List<ClinicSpecialtyScheduleTemplateDTO> getAllClinicSpecialtyScheduleTemplates(UUID clinicSpecialtyId) {
        List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyScheduleTemplates = clinicSpecialtyScheduleTemplateRepository.findAllByClinicSpecialtyId(clinicSpecialtyId);

        if (clinicSpecialtyScheduleTemplates.isEmpty()) {
            return List.of();
        }

        return ClinicSpecialtyScheduleTemplateMapper.toClinicSpecialtyScheduleTemplateResponseDTO(clinicSpecialtyScheduleTemplates);
    }

    public boolean checkClinicSpecialtyScheduleExists(UUID clinicSpecialtyId) {
        return clinicSpecialtyScheduleTemplateRepository.existsByClinicSpecialtyId(clinicSpecialtyId);
    }

    @Transactional
    public void addedDoctor(UUID clinicSpecialtyId, UUID doctorId) {
        refixDoctorScheduleTemplatesInClinicSpecialty(clinicSpecialtyId, doctorId);
        List<ClinicSpecialtyScheduleTemplate> clinicSpecialtyScheduleTemplate =
                clinicSpecialtyScheduleTemplateRepository.findAllByClinicSpecialtyId(clinicSpecialtyId);
        Optional<ClinicSpecialtyDoctor> clinicSpecialtyDoctorOpt =
                clinicSpecialtyDoctorRepository.findByClinicSpecialtyIdAndDoctorId(clinicSpecialtyId, doctorId);

        if (clinicSpecialtyDoctorOpt.isEmpty()) {
            throw new RuntimeException("ClinicSpecialtyDoctor not found for clinicSpecialtyId: " + clinicSpecialtyId + " and doctorId: " + doctorId);
        }

        for (ClinicSpecialtyScheduleTemplate template : clinicSpecialtyScheduleTemplate) {
            doctorAvailableSlotService.generateSlots(template, doctorId);
        }
    }

    public void refixDoctorScheduleTemplatesInClinicSpecialty(UUID clinicSpecialtyId, UUID doctorId) {
        List<ClinicSpecialtyScheduleTemplate> clinicTemplates =
                clinicSpecialtyScheduleTemplateRepository.findAllByClinicSpecialtyId(clinicSpecialtyId);

        List<DayOfWeek> templateDays = clinicTemplates.stream()
                .map(ClinicSpecialtyScheduleTemplate::getDayOfWeek)
                .toList();

        List<DoctorScheduleTemplate> doctorTemplates =
                doctorScheduleTemplateRepository.findByDoctorIdAndDayOfWeekIn(doctorId, templateDays);

        adjustTime(clinicTemplates, doctorTemplates);
    }

    private void adjustTime(List<ClinicSpecialtyScheduleTemplate> clinicTemplates, List<DoctorScheduleTemplate> doctorTemplates) {
        for (ClinicSpecialtyScheduleTemplate clinicTemplate : clinicTemplates) {
            DayOfWeek day = clinicTemplate.getDayOfWeek();

            for (DoctorScheduleTemplate doctorTemplate : doctorTemplates) {
                if (doctorTemplate.getDayOfWeek() != day) continue;

                LocalTime clinicStart = clinicTemplate.getFromTime();
                LocalTime clinicEnd = clinicTemplate.getToTime();

                LocalTime doctorStart = doctorTemplate.getFromTime();
                LocalTime doctorEnd = doctorTemplate.getToTime();

                boolean isOverlaps = !(doctorEnd.isBefore(clinicStart.minusHours(1)) || doctorStart.isAfter(clinicEnd.plusHours(1)));
                if (isOverlaps) {
                    if((doctorStart.isBefore(clinicStart) && doctorEnd.isAfter(clinicEnd))
                            || (doctorStart.isAfter(clinicStart) || doctorStart.equals(clinicStart))
                            && (doctorEnd.isBefore(clinicEnd) || doctorEnd.equals(clinicEnd))
                            || (doctorStart.isBefore(clinicEnd) && doctorEnd.isAfter(clinicEnd))
                    ) {
                        doctorStart = clinicEnd.plusHours(1);
                    } else if ((doctorStart.isBefore(clinicStart) || doctorStart.equals(clinicStart)) && (doctorEnd.isAfter(clinicStart) || doctorEnd.equals(clinicStart))) {
                        doctorEnd = clinicStart.minusHours(1);
                    }

                    if (!doctorStart.isBefore(doctorEnd) && doctorEnd.isBefore(clinicStart)) {
                        doctorStart = doctorEnd.minusHours(1);
                    } else if (!doctorStart.isBefore(doctorEnd) && doctorStart.isAfter(clinicEnd)) {
                        doctorEnd = doctorStart.plusHours(1);
                    }

                    if (doctorStart.isBefore(LocalTime.of(6, 0)) ||
                            doctorEnd.isAfter(LocalTime.of(23, 0))) {
                        doctorAvailableSlotService.deleteAvailableSlotsByTemplateId(doctorTemplate.getId());
                        doctorScheduleTemplateRepository.delete(doctorTemplate);
                        continue;
                    }

                    doctorTemplate.setFromTime(doctorStart);
                    doctorTemplate.setToTime(doctorEnd);

                    doctorAvailableSlotService.deleteAvailableSlotsByTemplateId(doctorTemplate.getId());
                    doctorScheduleTemplateRepository.save(doctorTemplate);
                    doctorAvailableSlotService.generateSlots(doctorTemplate);
                }
            }
        }
    }

    public void refixDoctorScheduleTemplatesInClinicSpecialty(UUID clinicSpecialtyId) {
        List<ClinicSpecialtyScheduleTemplate> clinicTemplates =
                clinicSpecialtyScheduleTemplateRepository.findAllByClinicSpecialtyId(clinicSpecialtyId);

        List<ClinicSpecialtyDoctor> doctors =
                clinicSpecialtyDoctorRepository.findByClinicSpecialtyId(clinicSpecialtyId);

        List<UUID> doctorIds = doctors.stream()
                .map(ClinicSpecialtyDoctor::getDoctorId)
                .toList();

        List<DayOfWeek> templateDays = clinicTemplates.stream()
                .map(ClinicSpecialtyScheduleTemplate::getDayOfWeek)
                .toList();

        List<DoctorScheduleTemplate> doctorTemplates =
                doctorScheduleTemplateRepository.findByDoctorIdInAndDayOfWeekIn(doctorIds, templateDays);

        adjustTime(clinicTemplates, doctorTemplates);
    }


    @Transactional
    public void createClinicSpecialtyScheduleTemplate(
            UUID clinicSpecialtyId, List<ClinicSpecialtyScheduleTemplateDTO> newTemplates
    ) {
        List<ClinicSpecialtyScheduleTemplate> existingTemplates = clinicSpecialtyScheduleTemplateRepository.findAllByClinicSpecialtyId(clinicSpecialtyId);

        Map<DayOfWeek, ClinicSpecialtyScheduleTemplate> existingMap = existingTemplates.stream()
                .collect(Collectors.toMap(ClinicSpecialtyScheduleTemplate::getDayOfWeek, t -> t));

        List<ClinicSpecialtyScheduleTemplate> toKeep = new ArrayList<>();
        List<ClinicSpecialtyScheduleTemplateDTO> toCreate = new ArrayList<>();

        for (ClinicSpecialtyScheduleTemplateDTO dto : newTemplates) {
            ClinicSpecialtyScheduleTemplate existing = existingMap.get(dto.getDayOfWeek());
            if (existing != null &&
                    existing.getFromTime().equals(dto.getFromTime()) &&
                    existing.getToTime().equals(dto.getToTime()) &&
                    existing.getSlotDuration().equals(dto.getSlotDuration())) {
                toKeep.add(existing);
            } else {
                toCreate.add(dto);
            }
        }

        List<ClinicSpecialtyScheduleTemplate> toDelete = existingTemplates.stream()
                .filter(t -> toKeep.stream().noneMatch(k -> k.getDayOfWeek() == t.getDayOfWeek()))
                .toList();
        if (!toDelete.isEmpty()) {
            List<UUID> templateIds = toDelete.stream().map(ClinicSpecialtyScheduleTemplate::getId).toList();
            doctorAvailableSlotService.deleteAvailableSlotsByTemplateIdIn(templateIds);
            clinicSpecialtyScheduleTemplateRepository.deleteAll(toDelete);
        }
        for (ClinicSpecialtyScheduleTemplateDTO dto : toCreate) {
            ClinicSpecialtyScheduleTemplate entity = new ClinicSpecialtyScheduleTemplate();
            entity.setClinicSpecialtyId(clinicSpecialtyId);
            entity.setDayOfWeek(dto.getDayOfWeek());
            entity.setFromTime(dto.getFromTime());
            entity.setToTime(dto.getToTime());
            entity.setSlotDuration(dto.getSlotDuration());
            clinicSpecialtyScheduleTemplateRepository.save(entity);
            doctorAvailableSlotService.generateSlots(entity);
        }

        refixDoctorScheduleTemplatesInClinicSpecialty(clinicSpecialtyId);
    }
}
