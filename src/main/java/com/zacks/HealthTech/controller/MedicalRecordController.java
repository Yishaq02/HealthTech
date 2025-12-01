package com.zacks.HealthTech.controller;

import com.zacks.HealthTech.dto.request.MedicalRecordRequestDTO;
import com.zacks.HealthTech.dto.response.MedicalRecordResponseDTO;
import com.zacks.HealthTech.service.IMedicalRecordService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing medical records.
 * Medical records can only be created for completed appointments.
 * 
 * @see IMedicalRecordService
 */
@RestController
@RequestMapping("/api/v1/medical-records")
@RequiredArgsConstructor
public class MedicalRecordController {

    private final IMedicalRecordService medicalRecordService;

    /**
     * Creates a new medical record associated with a completed appointment.
     * 
     * @param request the medical record data including diagnosis, treatment, and
     *                notes
     * @return the created medical record
     * @throws jakarta.persistence.EntityNotFoundException if the appointment is not
     *                                                     found
     * @throws IllegalStateException                       if the appointment is not
     *                                                     marked as completed
     */
    @PostMapping
    public ResponseEntity<MedicalRecordResponseDTO> create(@Valid @RequestBody MedicalRecordRequestDTO request) {
        MedicalRecordResponseDTO response = medicalRecordService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a medical record by ID.
     * 
     * @param id the medical record UUID
     * @return the medical record details
     * @throws jakarta.persistence.EntityNotFoundException if not found
     */
    @GetMapping("/{id}")
    public ResponseEntity<MedicalRecordResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(medicalRecordService.findById(id));
    }
}