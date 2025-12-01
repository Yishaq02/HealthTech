package com.zacks.HealthTech.service;

import com.zacks.HealthTech.dto.request.MedicalRecordRequestDTO;
import com.zacks.HealthTech.dto.response.MedicalRecordResponseDTO;
import java.util.UUID;

/**
 * Service interface for medical record management.
 * Medical records document diagnoses and treatments from completed
 * appointments.
 */
public interface IMedicalRecordService {

    /**
     * Finds a medical record by its unique identifier.
     * 
     * @param id the medical record UUID
     * @return the medical record details
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    MedicalRecordResponseDTO findById(UUID id);

    /**
     * Creates a new medical record for a completed appointment.
     * 
     * @param request the medical record data including diagnosis, treatment, and
     *                notes
     * @return the created medical record
     * @throws jakarta.persistence.EntityNotFoundException if appointment not found
     */
    MedicalRecordResponseDTO create(MedicalRecordRequestDTO request);

    /**
     * Updates an existing medical record.
     * 
     * @param id      the medical record UUID
     * @param request the updated medical record data
     * @return the updated medical record
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    MedicalRecordResponseDTO update(UUID id, MedicalRecordRequestDTO request);

    /**
     * Deletes a medical record.
     * 
     * @param id the medical record UUID
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    void delete(UUID id);

}
