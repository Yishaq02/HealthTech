package com.zacks.HealthTech.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.AfterMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mapping;

import com.zacks.HealthTech.dto.request.DoctorRequestDTO;
import com.zacks.HealthTech.dto.response.DoctorsResponseDTO;
import com.zacks.HealthTech.model.Doctors;
import com.zacks.HealthTech.model.Users;
import com.zacks.HealthTech.model.Specialties;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface DoctorMapper {

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "specialty", ignore = true)
    @Mapping(target = "firstName", source = "name")
    @Mapping(target = "licenseNumber", source = "license")
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    Doctors toEntity(DoctorRequestDTO request);

    @AfterMapping
    default void setUserAndSpecialty(DoctorRequestDTO request, @MappingTarget Doctors entity) {
        if (request.userId() != null) {
            Users user = new Users();
            user.setId(request.userId());
            entity.setUser(user);
        }
        if (request.specialityId() != null) {
            Specialties specialty = new Specialties();
            specialty.setId(request.specialityId());
            entity.setSpecialty(specialty);
        }
    }

    default DoctorsResponseDTO toResponse(Doctors entity) {
        if (entity == null) {
            return null;
        }

        String email = null;
        if (entity.getUser() != null) {
            email = entity.getUser().getEmail();
        }

        String specialityName = null;
        if (entity.getSpecialty() != null) {
            specialityName = entity.getSpecialty().getName();
        }

        return new DoctorsResponseDTO(
                entity.getId(),
                entity.getFirstName(),
                entity.getLastName(),
                entity.getLicenseNumber(),
                entity.getConsultingRoom(),
                entity.getSpecialty() != null ? entity.getSpecialty().getId() : null,
                specialityName,
                email);
    }

}
