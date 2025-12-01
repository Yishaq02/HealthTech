package com.zacks.HealthTech.service;

import org.springframework.stereotype.Service;
import java.util.UUID;
import com.zacks.HealthTech.dto.request.DoctorRequestDTO;
import com.zacks.HealthTech.dto.response.DoctorsResponseDTO;
import com.zacks.HealthTech.enums.Role;
import com.zacks.HealthTech.repository.DoctorsRepository;
import com.zacks.HealthTech.repository.UsersRepository;
import com.zacks.HealthTech.repository.SpecialitiesRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

import com.zacks.HealthTech.mapper.DoctorMapper;
import com.zacks.HealthTech.model.Doctors;
import com.zacks.HealthTech.model.Specialties;
import com.zacks.HealthTech.model.Users;
import org.springframework.security.crypto.password.PasswordEncoder;

@Service
public class DoctorService implements IDoctorService {

    private final DoctorsRepository doctorsRepository;
    private final DoctorMapper doctorMapper;
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final SpecialitiesRepository specialitiesRepository;

    public DoctorService(DoctorsRepository doctorsRepository, DoctorMapper doctorMapper,
            UsersRepository usersRepository,
            PasswordEncoder passwordEncoder,
            SpecialitiesRepository specialitiesRepository) {
        this.doctorsRepository = doctorsRepository;
        this.doctorMapper = doctorMapper;
        this.usersRepository = usersRepository;
        this.passwordEncoder = passwordEncoder;
        this.specialitiesRepository = specialitiesRepository;
    }

    @Override
    public DoctorsResponseDTO findById(UUID id) {
        return doctorsRepository.findById(id)
                .map(doctorMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Doctor no encontrado con ID: " + id));
    }

    @Override
    @Transactional
    public DoctorsResponseDTO create(DoctorRequestDTO request) {
        Doctors doctorEntity = doctorMapper.toEntity(request);

        Specialties specialty = specialitiesRepository.findById(request.specialityId())
                .orElseThrow(() -> new RuntimeException("Especialidad no encontrada"));
        doctorEntity.setSpecialty(specialty);

        if (request.userId() == null) {
            Users newUser = new Users();
            String generatedEmail = request.name().toLowerCase() + "." +
                    request.lastName().toLowerCase() + "@hospital.com";
            newUser.setEmail(generatedEmail);
            newUser.setPassword(passwordEncoder.encode("TemporalPassword123"));
            newUser.setRole(Role.DOCTOR);
            newUser.setIsActive(true);
            doctorEntity.setUser(newUser);
        } else {
            Users existingUser = usersRepository.findById(request.userId())
                    .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
            doctorEntity.setUser(existingUser);
        }

        Doctors savedDoctor = doctorsRepository.save(doctorEntity);
        return doctorMapper.toResponse(savedDoctor);
    }

    @Override
    public DoctorsResponseDTO update(UUID id, DoctorRequestDTO request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

}
