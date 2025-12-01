package com.zacks.HealthTech.service;

import org.springframework.stereotype.Service;
import com.zacks.HealthTech.repository.UsersRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.zacks.HealthTech.mapper.UserMapper;
import com.zacks.HealthTech.model.Users;

import java.util.UUID;
import com.zacks.HealthTech.dto.request.UserRequestDTO;
import com.zacks.HealthTech.dto.response.UserResponseDTO;

@Service
public class UserService implements IUserService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;

    public UserService(UsersRepository usersRepository, UserMapper userMapper) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserResponseDTO findById(UUID id) {
        return usersRepository.findById(id)
                .map(userMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Usuario no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public UserResponseDTO create(UserRequestDTO request) {

        Users user = userMapper.toEntity(request);
        usersRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponseDTO update(UUID id, UserRequestDTO request) {
        return null;
    }

    @Override
    public void delete(UUID id) {

    }

}
