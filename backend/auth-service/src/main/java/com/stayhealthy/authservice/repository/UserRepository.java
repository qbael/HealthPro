package com.stayhealthy.authservice.repository;

import com.stayhealthy.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
}