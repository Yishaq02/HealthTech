package com.zacks.HealthTech.service;

import com.zacks.HealthTech.dto.request.PatientRequestDTO;
import com.zacks.HealthTech.dto.response.PatientResponseDTO;
import java.util.UUID;

/**
 * Service interface for patient management operations.
 * Handles patient registration, updates, and retrieval.
 */
public interface IPatientService {

    /**
     * Finds a patient by their unique identifier.
     * 
     * @param id the patient's UUID
     * @return the patient details
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    PatientResponseDTO findById(UUID id);

    /**
     * Registers a new patient in the system.
     * Patient is automatically marked as active upon creation.
     * 
     * @param request the patient registration data
     * @return the created patient with generated ID
     */
    PatientResponseDTO create(PatientRequestDTO request);

    /**
     * Updates an existing patient's information.
     * 
     * @param id      the patient's UUID
     * @param request the updated patient data
     * @return the updated patient details
     * @throws jakarta.persistence.EntityNotFoundException if patient not found
     */
    PatientResponseDTO update(UUID id, PatientRequestDTO request);

    /**
     * Deletes a patient from the system.
     * 
     * @param id the patient's UUID
     * @throws jakarta.persistence.EntityNotFoundException if patient not found
     */
    void delete(UUID id);

}
