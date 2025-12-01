package com.zacks.HealthTech.dto.request;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;

/**
 * Request DTO for patient registration.
 * 
 * @param name      patient's first name (required)
 * @param lastName  patient's last name (required)
 * @param birthDate date of birth, must be in the past (required)
 * @param phone     contact phone number (optional)
 */
public record PatientRequestDTO(

        @NotBlank(message = "Name is required") String name,
        @NotBlank(message = "Last name is required") String lastName,
        @NotNull(message = "Birth date is required") @Past(message = "Birth date must be in the past") LocalDate birthDate,
        String phone) {

}
