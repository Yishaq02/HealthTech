package com.zacks.HealthTech.service;

import java.util.UUID;
import com.zacks.HealthTech.dto.request.UserRequestDTO;
import com.zacks.HealthTech.dto.response.UserResponseDTO;

public interface IUserService {

    UserResponseDTO findById(UUID id);

    UserResponseDTO create(UserRequestDTO request);

    UserResponseDTO update(UUID id, UserRequestDTO request);

    void delete(UUID id);

}
