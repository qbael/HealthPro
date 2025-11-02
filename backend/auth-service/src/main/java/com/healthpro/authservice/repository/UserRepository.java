package com.healthpro.authservice.repository;

import com.healthpro.authservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
    boolean existsByEmailAndIdNot(String email, UUID id);

    Page<User> findByRoleRoleName(String roleName, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCase(String email, Pageable pageable);
    Page<User> findByRoleRoleNameAndEmailContainingIgnoreCase(String roleName, String email, Pageable pageable);
    Page<User> findByRoleRoleNameNot(String roleName, Pageable pageable);
    Page<User> findByRoleRoleNameAndRoleRoleNameNot(String roleName, String excludedRole, Pageable pageable);
    Page<User> findByEmailContainingIgnoreCaseAndRoleRoleNameNot(String email, String excludedRole, Pageable pageable);
    Page<User> findByRoleRoleNameAndEmailContainingIgnoreCaseAndRoleRoleNameNot(
            String roleName, String email, String excludedRole, Pageable pageable);
}