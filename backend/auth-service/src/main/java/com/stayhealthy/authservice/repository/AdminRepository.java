package com.stayhealthy.authservice.repository;

import com.stayhealthy.authservice.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AdminRepository extends JpaRepository<Admin, UUID> {
}