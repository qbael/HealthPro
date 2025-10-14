package com.healthpro.authservice.service;

import com.healthpro.authservice.entity.Role;
import com.healthpro.authservice.repository.RoleRepository;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role findByRole(String role){
        return roleRepository.findByRoleName(role);
    }
}
