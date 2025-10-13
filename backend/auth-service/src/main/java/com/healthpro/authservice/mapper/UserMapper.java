package com.healthpro.authservice.mapper;

import com.healthpro.authservice.dto.UserResponseDTO;
import com.healthpro.authservice.entity.User;

public class UserMapper {
    public static UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO userDTO = new UserResponseDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole().getRoleName());
        userDTO.setIsActive(user.getIsActive());

        return userDTO;
    }
}
