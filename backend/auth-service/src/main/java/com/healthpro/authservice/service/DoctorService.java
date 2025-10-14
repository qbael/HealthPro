package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.ClinicResponseDTO;
import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.Patient;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.ClinicMapper;
import com.healthpro.authservice.mapper.DoctorMapper;
import com.healthpro.authservice.mapper.PatientMapper;
import com.healthpro.authservice.repository.DoctorRepository;
import com.healthpro.authservice.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class DoctorService {
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;

    public DoctorService(DoctorRepository doctorRepository, UserRepository userRepository) {
        this.doctorRepository = doctorRepository;
        this.userRepository = userRepository;
    }

    public List<DoctorResponseDTO> getDoctors() {
        List<Doctor> doctors = doctorRepository.findAll();
        return doctors.stream().map(DoctorMapper::toDoctorResponseDTO).toList();
    }

    public DoctorResponseDTO findByUserId(UUID id) {
        Doctor doctor = doctorRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with id " + id + " not found"));

        return DoctorMapper.toDoctorResponseDTO(doctor);
    }

    @Transactional
    public void createDoctor(User createdUser) {
        Doctor doctor = new Doctor();
        doctor.setUser(createdUser);
        doctorRepository.save(doctor);
    }

//    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
//        Doctor doctor =  doctorRepository.findById(id).orElseThrow(
//                () -> new UserNotFoundException("User not found with this ID " + id));
//
//        if (doctorRepository.existsByEmailAndIdNot(doctorRequestDTO.getEmail(), id)) {
//            throw new EmailAlreadyExistsException("User with this email already exists" + userRequestDTO.getEmail());
//        }
//
//        user.setEmail(userRequestDTO.getEmail());
//        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
//        user.setRole(roleRepository.findByRoleName(userRequestDTO.getRole()));
//
//        User updatedUser = userRepository.save(user);
//        return UserMapper.toDoctorResponseDTO(updatedUser);
//    }
}
