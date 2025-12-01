package com.zacks.HealthTech.dto.request;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record MedicalRecordRequestDTO(

                @NotNull(message = "Appointments ID is required") UUID appointmentsId,
                @NotBlank(message = "Diagnosis is required") String diagnosis,
                @NotBlank(message = "Treatment is required") String treatment,
                String notes

) {

}
