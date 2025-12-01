package com.zacks.HealthTech.dto.response;

import java.util.UUID;

public record DoctorsResponseDTO(
        UUID id,
        String name,
        String lastName,
        String license,
        String consultingRoom,

        UUID specialityId,
        String specialityName,

        String email

) {

}
