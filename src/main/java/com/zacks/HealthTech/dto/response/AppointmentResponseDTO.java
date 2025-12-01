package com.zacks.HealthTech.dto.response;

import java.util.UUID;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zacks.HealthTech.enums.StatusAppointments;

public record AppointmentResponseDTO(
        UUID id,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm") LocalDateTime scheduledAt,
        StatusAppointments status,
        String reason,

        UUID patientId,
        String patientName,
        String patientLastName,

        UUID doctorId,
        String doctorName,
        String specialty

) {

}
