package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.DoctorRequestDTO;
import com.healthpro.authservice.dto.DoctorResponseDTO;
import com.healthpro.authservice.dto.UserRequestDTO;
import com.healthpro.authservice.dto.UserResponseDTO;
import com.healthpro.authservice.entity.Doctor;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.exception.UserNotFoundException;
import com.healthpro.authservice.mapper.DoctorMapper;
import com.healthpro.authservice.mapper.UserMapper;
import com.healthpro.authservice.repository.DoctorRepository;
import com.healthpro.authservice.repository.UserRepository;
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

    public DoctorResponseDTO findById(UUID id) {
        Doctor doctor = doctorRepository.findByUser_Id(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id.toString()));
        return DoctorMapper.toDoctorResponseDTO(doctor);
    }

    public DoctorResponseDTO createDoctor(DoctorRequestDTO doctorRequestDTO) {
        Doctor doctor = new Doctor();
        User user = userRepository.findById(doctorRequestDTO.getUserId())
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        doctor.setUser(user);
        doctor.setFullName(doctorRequestDTO.getFullName());
        doctor.setAddress(doctorRequestDTO.getAddress());
        doctorRepository.save(doctor);

        return DoctorMapper.toDoctorResponseDTO(doctor);
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
