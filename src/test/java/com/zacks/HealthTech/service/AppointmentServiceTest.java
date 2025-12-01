package com.zacks.HealthTech.service;

import com.zacks.HealthTech.dto.request.AppointmentRequestDTO;
import com.zacks.HealthTech.mapper.AppointmentMapper;
import com.zacks.HealthTech.model.Doctors;
import com.zacks.HealthTech.model.Patients;
import com.zacks.HealthTech.repository.AppointmentsRepository;
import com.zacks.HealthTech.repository.DoctorsRepository;
import com.zacks.HealthTech.repository.PatientsRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

/**
 * Unit tests for {@link AppointmentService}.
 * Uses Mockito to isolate business logic from database dependencies.
 * 
 * <p>
 * Tests verify appointment creation rules, especially the overbooking
 * prevention logic.
 */
@ExtendWith(MockitoExtension.class)
class AppointmentServiceTest {

    @Mock
    private AppointmentsRepository appointmentsRepository;

    @Mock
    private DoctorsRepository doctorsRepository;

    @Mock
    private PatientsRepository patientsRepository;

    @Mock
    private AppointmentMapper appointmentMapper;

    @InjectMocks
    private AppointmentService appointmentService;

    /**
     * Tests that appointment creation fails when a doctor already has an
     * appointment at the requested time.
     * This test verifies the overbooking prevention business rule.
     * 
     * <p>
     * <b>Given:</b> A doctor with an existing appointment at a specific time
     * <br>
     * <b>When:</b> A new appointment is requested for the same doctor and time
     * <br>
     * <b>Then:</b> An IllegalStateException is thrown and no appointment is saved
     * 
     * @see AppointmentService#create(AppointmentRequestDTO)
     */
    @Test
    @DisplayName("Debe lanzar error si el doctor está ocupado en ese horario")
    void create_ShouldThrowException_WhenDoctorIsBusy() {
        UUID doctorId = UUID.randomUUID();
        UUID patientId = UUID.randomUUID();
        LocalDateTime fechaCita = LocalDateTime.of(2025, 12, 1, 10, 0);

        AppointmentRequestDTO request = new AppointmentRequestDTO(
                patientId, doctorId, fechaCita, "Consulta prueba");

        when(doctorsRepository.findById(doctorId)).thenReturn(Optional.of(new Doctors()));
        when(patientsRepository.findById(patientId)).thenReturn(Optional.of(new Patients()));

        // Simulate doctor is busy at this time
        when(appointmentsRepository.existsByDoctorAndDate(doctorId, fechaCita)).thenReturn(true);

        assertThrows(IllegalStateException.class, () -> {
            appointmentService.create(request);
        });

        // Verify appointment was never saved due to business rule violation
        verify(appointmentsRepository, never()).save(any());
    }
}