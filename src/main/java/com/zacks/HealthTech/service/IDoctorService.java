package com.zacks.HealthTech.service;

import java.util.UUID;
import com.zacks.HealthTech.dto.request.DoctorRequestDTO;
import com.zacks.HealthTech.dto.response.DoctorsResponseDTO;

/**
 * Service interface for doctor business operations.
 * Manages doctor registration, user account creation, and specialty assignment.
 */
public interface IDoctorService {

    /**
     * Finds a doctor by their unique identifier.
     * 
     * @param id the doctor's UUID
     * @return the doctor details including user and specialty information
     * @throws jakarta.persistence.EntityNotFoundException if no doctor exists with
     *                                                     the given ID
     */
    DoctorsResponseDTO findById(UUID id);

    /**
     * Creates a new doctor in the system.
     * If no userId is provided, automatically creates a new user account with:
     * - Email: firstname.lastname@hospital.com
     * - Password: TemporalPassword123 (should be changed on first login)
     * - Role: DOCTOR
     * 
     * @param request the doctor creation request
     * @return the created doctor with generated ID
     * @throws jakarta.persistence.EntityNotFoundException if the specialty does not
     *                                                     exist
     * @throws RuntimeException                            if the user ID is
     *                                                     provided but not found
     */
    DoctorsResponseDTO create(DoctorRequestDTO request);

    /**
     * Updates an existing doctor's information.
     * 
     * @param id      the doctor's UUID
     * @param request the updated doctor data
     * @return the updated doctor details
     * @throws jakarta.persistence.EntityNotFoundException if doctor not found
     */
    DoctorsResponseDTO update(UUID id, DoctorRequestDTO request);

    /**
     * Deletes a doctor from the system.
     * 
     * @param id the doctor's UUID
     * @throws jakarta.persistence.EntityNotFoundException if doctor not found
     */
    void delete(UUID id);

}
