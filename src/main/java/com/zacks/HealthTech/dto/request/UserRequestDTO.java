package com.zacks.HealthTech.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import com.zacks.HealthTech.enums.Role;

public record UserRequestDTO(

        @NotBlank(message = "Email is required") @Email(message = "The Email format is invalid") String email,

        @NotBlank(message = "Password is required") @Size(min = 8, message = "Password must be at least 8 characters long") String password,

        @NotNull(message = "Role is required") Role role

) {
}
