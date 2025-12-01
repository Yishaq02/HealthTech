package com.zacks.HealthTech.dto.response;

import java.util.UUID;

public record MedicalRecordResponseDTO(

                UUID id,

                UUID appointmentsId,
                String patientId,

                String diagnosis,
                String treatment,
                String notes

) {

}
