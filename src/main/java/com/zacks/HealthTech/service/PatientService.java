package com.zacks.HealthTech.service;

import org.springframework.stereotype.Service;
import com.zacks.HealthTech.dto.request.PatientRequestDTO;
import com.zacks.HealthTech.dto.response.PatientResponseDTO;
import java.util.UUID;
import com.zacks.HealthTech.mapper.PatientMapper;
import com.zacks.HealthTech.model.Patients;
import com.zacks.HealthTech.repository.PatientsRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class PatientService implements IPatientService {

    private final PatientsRepository patientsRepository;
    private final PatientMapper patientMapper;

    public PatientService(PatientsRepository patientsRepository, PatientMapper patientMapper) {
        this.patientsRepository = patientsRepository;
        this.patientMapper = patientMapper;
    }

    @Override
    public PatientResponseDTO findById(UUID id) {
        return patientsRepository.findById(id)
                .map(patientMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Paciente no encontrado con ID: " + id));
    }

    @Override
    public PatientResponseDTO create(PatientRequestDTO request) {

        Patients user = patientMapper.toEntity(request);
        patientsRepository.save(user);
        return patientMapper.toResponse(user);
    }

    @Override
    public PatientResponseDTO update(UUID id, PatientRequestDTO request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

}
