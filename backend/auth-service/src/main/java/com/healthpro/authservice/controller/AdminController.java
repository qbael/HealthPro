package com.healthpro.authservice.controller;

import com.healthpro.authservice.dto.ApiResponseDTO;
import com.healthpro.authservice.entity.User;
import com.healthpro.authservice.service.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/api/v1/admins")
public class AdminController {

    private final AdminService adminService;

    // Helper method to check if caller is admin
    private void checkAdminRole(String userRole) {
        if (!"ADMIN".equals(userRole)) {
            throw new org.springframework.security.access.AccessDeniedException("Access denied: Admin role required");
        }
    }

    // ===== GET ALL USERS =====
//    @GetMapping("/users")
//    @Operation(summary = "Get all users with pagination and sorting")
//    public ResponseEntity<ApiResponseDTO<Page<User>>> getAllUsers(
//            @RequestHeader(value = "X-User-Role", required = false, defaultValue = "ADMIN") String userRole,
//            @RequestParam(required = false, defaultValue = "0") Integer page,
//            @RequestParam(required = false, defaultValue = "10") Integer limit,
//            @RequestParam(required = false, defaultValue = "id") String sortBy,
//            @RequestParam(required = false, defaultValue = "asc") String sortDir,
//            @RequestParam(required = false) String role,
//            @RequestParam(required = false) String search
//    ) {
//        checkAdminRole(userRole);
//
//        Pageable pageable = PageRequest.of(page, limit, Sort.by(
//                sortDir.equalsIgnoreCase("ASC") ? Sort.Direction.ASC : Sort.Direction.DESC, sortBy
//        ));
//        Page<User> users = adminService.getAllUsers(pageable, role, search);
//
//        if (users.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(ApiResponseDTO.error(404, "No users found"));
//        }
//
//        return ResponseEntity.ok(ApiResponseDTO.success(users, "Users retrieved successfully"));
//    }

    // ===== LOCK ACCOUNT =====
    @PostMapping("/users/{userId}/lock")
    @Operation(summary = "Lock a user account")
    public ResponseEntity<ApiResponseDTO<Void>> lockAccount(
            @RequestHeader(value = "X-User-Role", required = false, defaultValue = "ADMIN") String userRole,
            @PathVariable UUID userId
    ) {
        checkAdminRole(userRole);
        adminService.lockAccount(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(null, "Account locked successfully"));
    }

    // ===== UNLOCK ACCOUNT =====
    @PostMapping("/users/{userId}/unlock")
    @Operation(summary = "Unlock a user account")
    public ResponseEntity<ApiResponseDTO<Void>> unlockAccount(
            @RequestHeader(value = "X-User-Role", required = false, defaultValue = "ADMIN") String userRole,
            @PathVariable UUID userId
    ) {
        checkAdminRole(userRole);
        adminService.unlockAccount(userId);
        return ResponseEntity.ok(ApiResponseDTO.success(null, "Account unlocked successfully"));
    }
}