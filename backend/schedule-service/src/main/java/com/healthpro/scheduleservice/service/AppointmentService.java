package com.healthpro.scheduleservice.service;

import com.healthpro.scheduleservice.dto.AppointmentDataRequestDto;
import com.healthpro.scheduleservice.dto.AppointmentDataResponseDto;
import com.healthpro.scheduleservice.dto.AppointmentRequestDto;
import com.healthpro.scheduleservice.entity.Appointment;
import com.healthpro.scheduleservice.entity.DoctorAvailableSlot;
import com.healthpro.scheduleservice.repository.AppointmentRepository;
import com.healthpro.scheduleservice.repository.DoctorAvailableSlotRepository;
import jakarta.validation.constraints.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;
import java.util.UUID;

@Slf4j
@Service
public class AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DoctorAvailableSlotRepository doctorAvailableSlotRepository;
    private final WebClient webClient;

    public AppointmentService(AppointmentRepository appointmentRepository,
                              DoctorAvailableSlotRepository doctorAvailableSlotRepository,
                              WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:4004/api").build();
        this.doctorAvailableSlotRepository = doctorAvailableSlotRepository;
        this.appointmentRepository = appointmentRepository;
    }

    private record AppointmentInfoResponseDto(
            @NotNull(message = "ID bệnh nhân không được để trống") UUID patientId,
            @Size(message = "Tên bệnh nhân không được vượt quá 255 ký tự", max = 255)
            @NotBlank(message = "Tên bệnh nhân không được để trống") String patientName,
            @Email(message = "Email bệnh nhân không hợp lệ") String patientEmail,
            @Pattern(message = "Số điện thoại bệnh nhân không hợp lệ", regexp = "^0[0-9]{9}$") String patientPhone,
            UUID doctorId,
            @Size(message = "Tên bác sĩ không được vượt quá 255 ký tự", max = 255) @NotBlank(message = "Tên bác sĩ không được để trống") String doctorName,
            UUID clinicId,
            @Size(message = "Tên phòng khám không được vượt quá 255 ký tự", max = 255) String clinicName,
            @NotNull(message = "Địa chỉ nơi khám không được để trống") String address,
            @NotNull(message = "Số điện thoại phòng khám không được để trống") @Pattern(message = "Số điện thoại phòng khám không hợp lệ", regexp = "^0[0-9]{9}$") String phone
    ) {
    }

    private record ClinicSpecialtyInfoResponseDto(
            @NotNull(message = "ID chuyên khoa không được để trống") UUID clinicSpecialtyId,
            String specialtyName,
            UUID clinicId
    ) {
    }

    private ClinicSpecialtyInfoResponseDto getClinicSpecialtyInfo(UUID clinicSpecialtyId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v2/appointments/clinic-specialty-info")
                        .queryParam("clinicSpecialtyId", clinicSpecialtyId)
                        .build())
                .header("X-Internal-Service", "schedule-service")
                .retrieve()
                .bodyToMono(ClinicSpecialtyInfoResponseDto.class)
                .block();
    }

    private AppointmentInfoResponseDto getAppointmentInfo(UUID userId, UUID doctorId, UUID clinicId) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/v1/appointments/appointment-info")
                        .queryParam("userId", userId)
                        .queryParam("doctorId", doctorId)
                        .queryParam("clinicId", clinicId)
                        .build())
                .header("X-Internal-Service", "schedule-service")
                .retrieve()
                .bodyToMono(AppointmentInfoResponseDto.class)
                .block();
    }

    public AppointmentDataResponseDto getConfirmAppointmentData(AppointmentDataRequestDto dto) {
        ClinicSpecialtyInfoResponseDto clinicSpecialtyInfo = null;
        AppointmentInfoResponseDto appointmentInfo;
        if (dto.getClinicSpecialtyId() != null) {
            clinicSpecialtyInfo = getClinicSpecialtyInfo(dto.getClinicSpecialtyId());
            appointmentInfo = getAppointmentInfo(dto.getUserId(), null, clinicSpecialtyInfo.clinicId);
        } else {
            appointmentInfo = getAppointmentInfo(dto.getUserId(), dto.getDoctorId(), null);
        }

        return new AppointmentDataResponseDto(
                appointmentInfo.patientId,
                appointmentInfo.patientName,
                appointmentInfo.patientEmail,
                appointmentInfo.patientPhone,
                appointmentInfo.doctorId,
                appointmentInfo.doctorName,
                appointmentInfo.clinicId == null ? null : appointmentInfo.clinicId,
                appointmentInfo.clinicName == null ? null : appointmentInfo.clinicName,
                appointmentInfo.address,
                appointmentInfo.phone,
                clinicSpecialtyInfo == null ? null : clinicSpecialtyInfo.clinicSpecialtyId,
                clinicSpecialtyInfo == null ? null : clinicSpecialtyInfo.specialtyName
        );
    }

    public boolean createAppointment(AppointmentRequestDto dto) {
        Optional<DoctorAvailableSlot> slotOpt = doctorAvailableSlotRepository.findById(dto.getSlotId());
        if (slotOpt.isEmpty()) {
            return false;
        }
        Appointment appointment = Appointment.builder()
                .patientId(dto.getPatientId())
                .patientName(dto.getPatientName())
                .patientEmail(dto.getPatientEmail())
                .patientPhone(dto.getPatientPhone())
                .doctorId(slotOpt.get().getDoctorId())
                .doctorName(dto.getDoctorName() != null ? dto.getDoctorName() : "")
                .clinicId(dto.getClinicId())
                .clinicName(dto.getClinicName())
                .address(dto.getAddress())
                .phone(dto.getPhone())
                .clinicSpecialtyId(slotOpt.get().getClinicSpecialtyId())
                .specialtyName(dto.getSpecialtyName())
                .appointmentType(slotOpt.get().getAppointmentType())
                .appointmentDate(slotOpt.get().getAppointmentDate())
                .startTime(slotOpt.get().getStartTime())
                .endTime(slotOpt.get().getEndTime())
                .notes(dto.getNotes())
                .build();
        appointmentRepository.save(appointment);
        doctorAvailableSlotRepository.deleteById(slotOpt.get().getId());
        log.info("Created appointment: {}", appointment.getId());
        return true;
    }
}
