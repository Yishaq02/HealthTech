package com.zacks.HealthTech.dto.response;

import java.util.UUID;

public record SpecialitiesResponseDTO(

        UUID id,
        String name,
        String description

) {
}
