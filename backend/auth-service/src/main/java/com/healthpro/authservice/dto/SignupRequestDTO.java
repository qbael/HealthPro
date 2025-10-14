package com.healthpro.authservice.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDTO {
    @NotBlank(message = "Email không được để trống")
    @Email(message = "Email không hợp lệ")
    private String email;

    @NotBlank(message = "Password không được để trống")
    @Size(min = 8, max = 255, message = "Password phải có ít nhất 8 ký tự")
    private String password;

    @NotNull(message = "Role không được để trống")
    private String role;

    @NotBlank(message = "PhoneNumber không được để trống")
    @Pattern(regexp = "^0[0-9]{9}$", message = "Số điện thoại phải có đúng 10 chữ số")
    private String phoneNumber;
}
