package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.UserRequestDTO;
import com.healthpro.authservice.dto.UserResponseDTO;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.EmailAlreadyExistsException;
import com.healthpro.authservice.mapper.UserMapper;
import com.healthpro.authservice.repository.RoleRepository;
import com.healthpro.authservice.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.healthpro.authservice.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
public class UserService {

    public final UserRepository userRepository;
    public final RoleRepository roleRepository;

    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public List<UserResponseDTO> getUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map(UserMapper::toUserResponseDTO).toList();
    }

    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public UserResponseDTO updateUser(UUID id, UserRequestDTO userRequestDTO) {
        User user =  userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException("User not found with this ID " + id));

        if (userRepository.existsByEmailAndIdNot(userRequestDTO.getEmail(), id)) {
            throw new EmailAlreadyExistsException("User with this email already exists" + userRequestDTO.getEmail());
        }

        user.setEmail(userRequestDTO.getEmail());
        user.setPhoneNumber(userRequestDTO.getPhoneNumber());
        user.setRole(roleRepository.findByRoleName(userRequestDTO.getRole()));

        User updatedUser = userRepository.save(user);
        return UserMapper.toUserResponseDTO(updatedUser);
    }

    public void deleteUser(UUID id) {
        userRepository.deleteById(id);
    }
}
