package com.healthpro.authservice.dto;

import com.healthpro.authservice.entity.Role;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDTO {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    @Size(max = 255, message = "Email không được vượt quá 255 ký tự")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại phải có đúng 10 chữ số")
    @Column(name = "phone_number", length = 10)
    private String phoneNumber;

    @NotNull(message = "Role không được để trống")
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private String role;
}
