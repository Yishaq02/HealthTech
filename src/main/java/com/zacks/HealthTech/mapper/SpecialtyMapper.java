package com.zacks.HealthTech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.zacks.HealthTech.model.Specialties;
import com.zacks.HealthTech.dto.response.SpecialitiesResponseDTO;
import com.zacks.HealthTech.dto.request.SpecialityRequestDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface SpecialtyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Specialties toEntity(SpecialityRequestDTO request);

    SpecialitiesResponseDTO toResponse(Specialties entity);

}
