package com.zacks.HealthTech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

import com.zacks.HealthTech.model.MedicalRecords;
import com.zacks.HealthTech.model.Appointments;
import com.zacks.HealthTech.dto.response.MedicalRecordResponseDTO;
import com.zacks.HealthTech.dto.request.MedicalRecordRequestDTO;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface MedicalRecordMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "appointment", ignore = true)
    MedicalRecords toEntity(MedicalRecordRequestDTO request);

    @AfterMapping
    default void setAppointment(MedicalRecordRequestDTO request, @MappingTarget MedicalRecords entity) {
        if (request.appointmentsId() != null) {
            Appointments appointment = new Appointments();
            appointment.setId(request.appointmentsId());
            entity.setAppointment(appointment);
        }
    }

    default MedicalRecordResponseDTO toResponse(MedicalRecords entity) {
        if (entity == null) {
            return null;
        }

        String patientId = null;
        if (entity.getAppointment() != null && entity.getAppointment().getPatient() != null) {
            patientId = entity.getAppointment().getPatient().getId().toString();
        }

        return new MedicalRecordResponseDTO(
                entity.getId(),
                entity.getAppointment() != null ? entity.getAppointment().getId() : null,
                patientId,
                entity.getDiagnosis(),
                entity.getTreatment(),
                entity.getNotes());
    }

}
