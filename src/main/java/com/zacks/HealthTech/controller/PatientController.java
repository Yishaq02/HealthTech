package com.zacks.HealthTech.controller;

import com.zacks.HealthTech.dto.request.PatientRequestDTO;
import com.zacks.HealthTech.dto.response.PatientResponseDTO;
import com.zacks.HealthTech.service.IPatientService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing patients in the HealthTech system.
 * Provides endpoints for patient registration and retrieval.
 * 
 * @see IPatientService
 */
@RestController
@RequestMapping("/api/v1/patients")
@RequiredArgsConstructor
public class PatientController {

    private final IPatientService patientService;

    /**
     * Registers a new patient in the system.
     * 
     * @param request the patient registration request with personal and insurance
     *                information
     * @return the created patient with generated ID
     * @throws IllegalArgumentException if patient data validation fails
     */
    @PostMapping
    public ResponseEntity<PatientResponseDTO> create(@Valid @RequestBody PatientRequestDTO request) {
        PatientResponseDTO response = patientService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a patient by their unique identifier.
     * 
     * @param id the patient's UUID
     * @return the patient details
     * @throws jakarta.persistence.EntityNotFoundException if no patient exists with
     *                                                     the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(patientService.findById(id));
    }
}