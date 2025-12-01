package com.zacks.HealthTech.controller;

import com.zacks.HealthTech.dto.request.AppointmentRequestDTO;
import com.zacks.HealthTech.dto.response.AppointmentResponseDTO;
import com.zacks.HealthTech.enums.StatusAppointments;
import com.zacks.HealthTech.service.IAppointmentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing medical appointments.
 * Handles appointment scheduling, retrieval, and status updates.
 * 
 * @see IAppointmentService
 */
@RestController
@RequestMapping("/api/v1/appointments")
@RequiredArgsConstructor
public class AppointmentController {

    private final IAppointmentService appointmentService;

    /**
     * Creates a new appointment in the system.
     * Validates that the doctor is available at the requested time.
     * 
     * @param request the appointment request with doctor, patient, and scheduled
     *                time
     * @return the created appointment
     * @throws jakarta.persistence.EntityNotFoundException if doctor or patient not
     *                                                     found
     * @throws IllegalStateException                       if doctor already has an
     *                                                     appointment at that time
     */
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> create(@Valid @RequestBody AppointmentRequestDTO request) {
        AppointmentResponseDTO response = appointmentService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves an appointment by ID.
     * 
     * @param id the appointment UUID
     * @return the appointment details
     * @throws jakarta.persistence.EntityNotFoundException if appointment not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(appointmentService.findById(id));
    }

    /**
     * Retrieves all appointments for a specific doctor, ordered by scheduled time
     * (descending).
     * 
     * @param doctorId the doctor's UUID
     * @return list of appointments for the doctor
     * @throws jakarta.persistence.EntityNotFoundException if doctor not found
     */
    @GetMapping("/doctor/{doctorId}")
    public ResponseEntity<Page<AppointmentResponseDTO>> findByDoctor(
            @PathVariable UUID doctorId,
            @PageableDefault(page = 0, size = 10, sort = "scheduledAt", direction = Sort.Direction.DESC) Pageable pageable) {
        return ResponseEntity.ok(appointmentService.findByDoctor(doctorId, pageable));
    }

    /**
     * Updates the status of an appointment.
     * Business rules prevent certain state transitions (e.g., cannot reactivate
     * canceled appointments).
     * 
     * @param id     the appointment UUID
     * @param status the new status
     * @return 204 No Content on success
     * @throws jakarta.persistence.EntityNotFoundException if appointment not found
     * @throws IllegalStateException                       if the status transition
     *                                                     is not allowed
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> updateStatus(@PathVariable UUID id,
            @RequestParam StatusAppointments status) {
        appointmentService.updateStatus(id, status);
        return ResponseEntity.noContent().build();
    }
}