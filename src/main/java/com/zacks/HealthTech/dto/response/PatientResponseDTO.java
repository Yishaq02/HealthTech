package com.zacks.HealthTech.dto.response;

import java.time.LocalDate;
import java.util.UUID;

public record PatientResponseDTO(
                UUID id,
                String name,
                String lastName,
                LocalDate birthDate,
                String phone,
                Boolean isActive

) {
}
