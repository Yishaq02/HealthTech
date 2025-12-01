package com.zacks.HealthTech.mapper;

import com.zacks.HealthTech.dto.request.AppointmentRequestDTO;
import com.zacks.HealthTech.dto.response.AppointmentResponseDTO;
import com.zacks.HealthTech.model.Appointments;
import com.zacks.HealthTech.model.Doctors;
import com.zacks.HealthTech.model.Patients;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AppointmentMapper {

    @Mapping(target = "patient", ignore = true)
    @Mapping(target = "doctor", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "status", constant = "SCHEDULED")
    Appointments toEntity(AppointmentRequestDTO request);

    @AfterMapping
    default void setPatientAndDoctor(AppointmentRequestDTO request, @MappingTarget Appointments entity) {
        if (request.patientId() != null) {
            Patients patient = new Patients();
            patient.setId(request.patientId());
            entity.setPatient(patient);
        }
        if (request.doctorId() != null) {
            Doctors doctor = new Doctors();
            doctor.setId(request.doctorId());
            entity.setDoctor(doctor);
        }
    }

    default AppointmentResponseDTO toResponse(Appointments entity) {
        if (entity == null) {
            return null;
        }

        String patientName = null;
        String patientLastName = null;
        if (entity.getPatient() != null) {
            patientName = entity.getPatient().getName();
            patientLastName = entity.getPatient().getLastName();
        }

        String doctorName = null;
        String specialty = null;
        if (entity.getDoctor() != null) {
            doctorName = entity.getDoctor().getFirstName() + " " + entity.getDoctor().getLastName();
            if (entity.getDoctor().getSpecialty() != null) {
                specialty = entity.getDoctor().getSpecialty().getName();
            }
        }

        return new AppointmentResponseDTO(
                entity.getId(),
                entity.getScheduledAt(),
                entity.getStatus(),
                entity.getReason(),
                entity.getPatient() != null ? entity.getPatient().getId() : null,
                patientName,
                patientLastName,
                entity.getDoctor() != null ? entity.getDoctor().getId() : null,
                doctorName,
                specialty);
    }

}