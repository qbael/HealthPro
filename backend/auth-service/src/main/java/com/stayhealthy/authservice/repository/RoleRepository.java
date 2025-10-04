package com.stayhealthy.authservice.repository;

import com.stayhealthy.authservice.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RoleRepository extends JpaRepository<Role, UUID> {
}