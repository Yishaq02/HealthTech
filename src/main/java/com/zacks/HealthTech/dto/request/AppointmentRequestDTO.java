package com.zacks.HealthTech.dto.request;

import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record AppointmentRequestDTO(

                @NotNull(message = "Patient ID is required") UUID patientId,
                @NotNull(message = "Doctor ID is required") UUID doctorId,
                @NotNull(message = "Scheduled at is required") @Future(message = "Scheduled at must be in the future") LocalDateTime scheduledAt,
                @NotNull(message = "Reason is required") @Size(min = 1, max = 255, message = "Reason must be between 1 and 255 characters") String reason

) {

}
