package com.zacks.HealthTech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

import com.zacks.HealthTech.model.Users;
import com.zacks.HealthTech.dto.response.UserResponseDTO;
import com.zacks.HealthTech.dto.request.UserRequestDTO;
import org.mapstruct.Mapping;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface UserMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "isActive", constant = "true")
    @Mapping(target = "password", ignore = true)
    Users toEntity(UserRequestDTO request);

    UserResponseDTO toResponse(Users entity);

}
