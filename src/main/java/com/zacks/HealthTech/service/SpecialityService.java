package com.zacks.HealthTech.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.zacks.HealthTech.dto.request.SpecialityRequestDTO;
import com.zacks.HealthTech.dto.response.SpecialitiesResponseDTO;
import com.zacks.HealthTech.repository.SpecialitiesRepository;

import jakarta.persistence.EntityNotFoundException;

import com.zacks.HealthTech.mapper.SpecialtyMapper;
import com.zacks.HealthTech.model.Specialties;

@Service
public class SpecialityService implements ISpecialityService {

    private final SpecialitiesRepository specialitiesRepository;
    private final SpecialtyMapper specialtyMapper;

    public SpecialityService(SpecialitiesRepository specialitiesRepository, SpecialtyMapper specialtyMapper) {
        this.specialitiesRepository = specialitiesRepository;
        this.specialtyMapper = specialtyMapper;
    }

    @Override
    public SpecialitiesResponseDTO findById(UUID id) {
        return specialitiesRepository.findById(id)
                .map(specialtyMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Especialidad no encontrada con ID: " + id));
    }

    @Override
    public SpecialitiesResponseDTO create(SpecialityRequestDTO request) {
        Specialties speciality = specialtyMapper.toEntity(request);
        specialitiesRepository.save(speciality);
        return specialtyMapper.toResponse(speciality);
    }

    @Override
    public SpecialitiesResponseDTO update(UUID id, SpecialityRequestDTO request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

}
