package com.zacks.HealthTech.service;

import java.util.UUID;
import com.zacks.HealthTech.dto.request.SpecialityRequestDTO;
import com.zacks.HealthTech.dto.response.SpecialitiesResponseDTO;

public interface ISpecialityService {

    SpecialitiesResponseDTO findById(UUID id);

    SpecialitiesResponseDTO create(SpecialityRequestDTO request);

    SpecialitiesResponseDTO update(UUID id, SpecialityRequestDTO request);

    void delete(UUID id);

}
