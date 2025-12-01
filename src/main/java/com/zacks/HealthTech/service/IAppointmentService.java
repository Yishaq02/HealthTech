package com.zacks.HealthTech.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.zacks.HealthTech.dto.request.AppointmentRequestDTO;
import com.zacks.HealthTech.dto.response.AppointmentResponseDTO;
import com.zacks.HealthTech.enums.StatusAppointments;

/**
 * Service interface for appointment management operations.
 * Handles appointment scheduling, retrieval, and status workflows.
 */
public interface IAppointmentService {

    /**
     * Finds an appointment by its unique identifier.
     * 
     * @param id the appointment UUID
     * @return the appointment details
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    AppointmentResponseDTO findById(UUID id);

    /**
     * Retrieves all appointments for a specific doctor.
     * Results are ordered by scheduled time in descending order (most recent
     * first).
     * 
     * @param doctorId the doctor's UUID
     * @return list of appointments
     * @throws jakarta.persistence.EntityNotFoundException if doctor does not exist
     */
    Page<AppointmentResponseDTO> findByDoctor(UUID doctorId, Pageable pageable);

    /**
     * Creates a new appointment.
     * Validates that the doctor does not have a conflicting appointment at the
     * requested time.
     * 
     * @param request the appointment details
     * @return the created appointment
     * @throws jakarta.persistence.EntityNotFoundException if doctor or patient not
     *                                                     found
     * @throws IllegalStateException                       if doctor is not
     *                                                     available at the
     *                                                     requested time
     */
    AppointmentResponseDTO create(AppointmentRequestDTO request);

    /**
     * Updates the status of an existing appointment.
     * Business rules:
     * - Completed appointments cannot change status
     * - Canceled appointments can only return to SCHEDULED status
     * 
     * @param id     the appointment UUID
     * @param status the new status
     * @throws jakarta.persistence.EntityNotFoundException if appointment not found
     * @throws IllegalStateException                       if the status transition
     *                                                     violates business rules
     */
    void updateStatus(UUID id, StatusAppointments status);

}
