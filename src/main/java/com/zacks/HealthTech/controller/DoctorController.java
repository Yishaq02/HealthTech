package com.zacks.HealthTech.controller;

import com.zacks.HealthTech.dto.request.DoctorRequestDTO;
import com.zacks.HealthTech.dto.response.DoctorsResponseDTO;
import com.zacks.HealthTech.service.IDoctorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * REST controller for managing doctors in the HealthTech system.
 * Provides endpoints for CRUD operations on doctor resources.
 * 
 * @see IDoctorService
 */
@RestController
@RequestMapping("/api/v1/doctors")
@RequiredArgsConstructor
public class DoctorController {

    private final IDoctorService doctorService;

    /**
     * Creates a new doctor in the system.
     * If userId is not provided, creates a new user automatically with generated
     * credentials.
     * 
     * @param request the doctor creation request containing name, specialty, and
     *                license
     * @return the created doctor with generated ID and user details
     * @throws jakarta.persistence.EntityNotFoundException if the specified
     *                                                     specialty does not exist
     * @throws IllegalArgumentException                    if the license number is
     *                                                     already in use
     */
    @PostMapping
    public ResponseEntity<DoctorsResponseDTO> create(@Valid @RequestBody DoctorRequestDTO request) {
        DoctorsResponseDTO response = doctorService.create(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    /**
     * Retrieves a doctor by their unique identifier.
     * 
     * @param id the doctor's UUID
     * @return the doctor details
     * @throws jakarta.persistence.EntityNotFoundException if no doctor exists with
     *                                                     the given ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<DoctorsResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(doctorService.findById(id));
    }
}