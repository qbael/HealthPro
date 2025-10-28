package com.healthpro.clinicservice.mapper;

import com.healthpro.clinicservice.dto.ClinicInvitationClinicDTO;
import com.healthpro.clinicservice.dto.ClinicInvitationDoctorDTO;
import com.healthpro.clinicservice.entity.ClinicInvitation;

public class ClinicInvitationDTOMapper {
    public static ClinicInvitationClinicDTO toClinicDTO(ClinicInvitation clinicInvitation) {
        ClinicInvitationClinicDTO clinicInvitationClinicDTO = new ClinicInvitationClinicDTO();
        clinicInvitationClinicDTO.setId(clinicInvitation.getId());
        clinicInvitationClinicDTO.setStatus(clinicInvitation.getStatus());
        clinicInvitationClinicDTO.setInvitedAt(clinicInvitation.getInvitedAt());
        clinicInvitationClinicDTO.setRespondedAt(clinicInvitation.getRespondedAt());
        clinicInvitationClinicDTO.setClinicSpecialtyId(clinicInvitation.getClinicSpecialty().getId());
        clinicInvitationClinicDTO.setSpecialtyId(clinicInvitation.getClinicSpecialty().getSpecialty().getId());
        clinicInvitationClinicDTO.setSpecialtyName(clinicInvitation.getClinicSpecialty().getSpecialty().getName());
        clinicInvitationClinicDTO.setClinicId(clinicInvitation.getClinicSpecialty().getClinic().getId());
        clinicInvitationClinicDTO.setClinicName(clinicInvitation.getClinicSpecialty().getClinic().getClinicName());
        clinicInvitationClinicDTO.setAddress(clinicInvitation.getClinicSpecialty().getClinic().getAddress());
        clinicInvitationClinicDTO.setDescription(clinicInvitation.getClinicSpecialty().getClinic().getDescription());
        clinicInvitationClinicDTO.setWeekdayOpenHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayOpenHour());
        clinicInvitationClinicDTO.setWeekdayCloseHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayCloseHour());
        clinicInvitationClinicDTO.setWeekendOpenHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekendOpenHour());
        clinicInvitationClinicDTO.setWeekendCloseHour(clinicInvitation.getClinicSpecialty().getClinic().getWeekdayCloseHour());
        clinicInvitationClinicDTO.setLogoUrl(clinicInvitation.getClinicSpecialty().getClinic().getLogoUrl());
        clinicInvitationClinicDTO.setAvatarUrl(clinicInvitation.getClinicSpecialty().getClinic().getAvatarUrl());
        return clinicInvitationClinicDTO;
    }

    public static ClinicInvitationDoctorDTO toDoctorDTO(ClinicInvitation clinicInvitation) {
        ClinicInvitationDoctorDTO clinicInvitationDoctorDTO = new ClinicInvitationDoctorDTO();
        clinicInvitationDoctorDTO.setId(clinicInvitation.getId());
        clinicInvitationDoctorDTO.setStatus(clinicInvitation.getStatus());
        clinicInvitationDoctorDTO.setInvitedAt(clinicInvitation.getInvitedAt());
        clinicInvitationDoctorDTO.setRespondedAt(clinicInvitation.getRespondedAt());
        clinicInvitationDoctorDTO.setClinicSpecialtyId(clinicInvitation.getClinicSpecialty().getId());
        clinicInvitationDoctorDTO.setSpecialtyId(clinicInvitation.getClinicSpecialty().getSpecialty().getId());
        clinicInvitationDoctorDTO.setSpecialtyName(clinicInvitation.getClinicSpecialty().getSpecialty().getName());
        clinicInvitationDoctorDTO.setDoctorId(clinicInvitation.getDoctor().getId());
        clinicInvitationDoctorDTO.setFullName(clinicInvitation.getDoctor().getFullName());
        clinicInvitationDoctorDTO.setBio(clinicInvitation.getDoctor().getBio());
        clinicInvitationDoctorDTO.setGender(clinicInvitation.getDoctor().getGender());
        clinicInvitationDoctorDTO.setAddress(clinicInvitation.getDoctor().getAddress());
        clinicInvitationDoctorDTO.setAvatarUrl(clinicInvitation.getDoctor().getAvatarUrl());
        return clinicInvitationDoctorDTO;
    }
}
