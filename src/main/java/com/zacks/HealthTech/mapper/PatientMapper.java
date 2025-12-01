package com.zacks.HealthTech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import com.zacks.HealthTech.dto.request.PatientRequestDTO;
import com.zacks.HealthTech.dto.response.PatientResponseDTO;
import com.zacks.HealthTech.model.Patients;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface PatientMapper {
    @Mapping(target = "dob", source = "birthDate")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "insurance", constant = "true")
    Patients toEntity(PatientRequestDTO request);

    @Mapping(target = "birthDate", source = "dob")
    PatientResponseDTO toResponse(Patients entity);
}
