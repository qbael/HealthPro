package com.healthpro.clinicservice.mapper;

import com.healthpro.clinicservice.dto.ClinicInvitationResponseDTO;
import com.healthpro.clinicservice.entity.ClinicInvitation;

public class ClinicInvitationDTOMapper {
    public static ClinicInvitationResponseDTO toDTO(ClinicInvitation clinicInvitation) {
        ClinicInvitationResponseDTO clinicInvitationResponseDTO = new ClinicInvitationResponseDTO();
        clinicInvitationResponseDTO.setId(clinicInvitation.getId());
        clinicInvitationResponseDTO.setStatus(clinicInvitation.getStatus());
        clinicInvitationResponseDTO.setInvitedAt(clinicInvitation.getInvitedAt());
        clinicInvitationResponseDTO.setRespondedAt(clinicInvitation.getRespondedAt());
        clinicInvitationResponseDTO.setTitle(clinicInvitation.getTitle());
        clinicInvitationResponseDTO.setClinicSpecialtyId(clinicInvitation.getClinicSpecialty().getId());
        clinicInvitationResponseDTO.setSpecialtyId(clinicInvitation.getClinicSpecialty().getSpecialty().getId());
        clinicInvitationResponseDTO.setSpecialtyName(clinicInvitation.getClinicSpecialty().getSpecialty().getName());
        clinicInvitationResponseDTO.setClinicId(clinicInvitation.getClinicSpecialty().getClinic().getId());
        clinicInvitationResponseDTO.setClinicName(clinicInvitation.getClinicSpecialty().getClinic().getClinicName());
        clinicInvitationResponseDTO.setAddress(clinicInvitation.getClinicSpecialty().getClinic().getAddress());
        clinicInvitationResponseDTO.setDescription(clinicInvitation.getClinicSpecialty().getClinic().getDescription());
        clinicInvitationResponseDTO.setWeekdayOpenHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayOpenHour());
        clinicInvitationResponseDTO.setWeekdayCloseHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayCloseHour());
        clinicInvitationResponseDTO.setWeekendOpenHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekendOpenHour());
        clinicInvitationResponseDTO.setWeekendCloseHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayCloseHour());
        clinicInvitationResponseDTO.setLogoUrl(clinicInvitation.getClinicSpecialty().getClinic().getLogoUrl());
        clinicInvitationResponseDTO.setAvatarUrl(clinicInvitation.getClinicSpecialty().getClinic().getAvatarUrl());
        return clinicInvitationResponseDTO;
    }
}
