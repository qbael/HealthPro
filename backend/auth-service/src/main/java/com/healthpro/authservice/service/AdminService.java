package com.healthpro.authservice.service;

import com.healthpro.authservice.dto.ApiResponseDTO;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.exception.ForbiddenException;
import com.healthpro.authservice.exception.ResourceNotFoundException;
import com.healthpro.authservice.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminService {

    private final UserRepository userRepository;

    public AdminService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

//    public Page<User> getAllUsers(Pageable pageable, String role, String search) {
//        if (role != null && !"ALL".equals(role)) {
//            if (search != null && !search.trim().isEmpty()) {
//                return userRepository.findByRoleRoleNameAndEmailContainingIgnoreCaseAndRoleRoleNameNot(role, search, "ADMIN", pageable);
//            } else {
//                return userRepository.findByRoleRoleNameAndRoleRoleNameNot(role, "ADMIN", pageable);
//            }
//        } else if (search != null && !search.trim().isEmpty()) {
//            return userRepository.findByEmailContainingIgnoreCaseAndRoleRoleNameNot(search, "ADMIN", pageable);
//        } else {
//            return userRepository.findByRoleRoleNameNot("ADMIN", pageable);
//        }
//    }

    public void lockAccount(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        if ("ADMIN".equals(user.getRole().getRoleName())) {
            throw new ForbiddenException("Cannot lock admin accounts");
        }

        user.setIsActive(false);
        userRepository.save(user);
    }

    // Unlock account: Set active = true
    public void unlockAccount(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));

        user.setIsActive(true);
        userRepository.save(user);
    }
}