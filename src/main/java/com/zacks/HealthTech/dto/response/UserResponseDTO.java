package com.zacks.HealthTech.dto.response;

import com.zacks.HealthTech.enums.Role;
import java.util.UUID;

public record UserResponseDTO(

        UUID id,
        String email,
        Role role,
        Boolean isActive

) {
}
