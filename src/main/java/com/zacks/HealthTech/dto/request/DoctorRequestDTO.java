package com.zacks.HealthTech.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DoctorRequestDTO(
        UUID userId,

        @NotNull(message = "Speciality ID is required") UUID specialityId,
        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Last name is required") String lastName,
        @NotBlank(message = "License is required") String license,
        @NotBlank(message = "Consulting room is required") String consultingRoom

) {

}
