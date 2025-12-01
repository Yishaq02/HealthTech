package com.zacks.HealthTech.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.zacks.HealthTech.dto.request.AppointmentRequestDTO;
import com.zacks.HealthTech.dto.response.AppointmentResponseDTO;
import com.zacks.HealthTech.enums.StatusAppointments;
import com.zacks.HealthTech.repository.AppointmentsRepository;
import com.zacks.HealthTech.repository.DoctorsRepository;
import com.zacks.HealthTech.repository.PatientsRepository;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import com.zacks.HealthTech.mapper.AppointmentMapper;
import com.zacks.HealthTech.model.Appointments;
import com.zacks.HealthTech.model.Doctors;
import com.zacks.HealthTech.model.Patients;

@Service
public class AppointmentService implements IAppointmentService {

    private final AppointmentsRepository appointmentsRepository;
    private final AppointmentMapper appointmentMapper;
    private final DoctorsRepository doctorsRepository;
    private final PatientsRepository patientsRepository;

    public AppointmentService(AppointmentsRepository appointmentsRepository, AppointmentMapper appointmentMapper,
            DoctorsRepository doctorsRepository, PatientsRepository patientsRepository) {
        this.appointmentsRepository = appointmentsRepository;
        this.appointmentMapper = appointmentMapper;
        this.doctorsRepository = doctorsRepository;
        this.patientsRepository = patientsRepository;
    }

    @Override
    public AppointmentResponseDTO findById(UUID id) {
        return appointmentsRepository.findById(id)
                .map(appointmentMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada con ID: " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public Page<AppointmentResponseDTO> findByDoctor(UUID doctorId, Pageable pageable) {
        // 1. Validar Doctor
        if (!doctorsRepository.existsById(doctorId)) {
            throw new EntityNotFoundException("Doctor no encontrado con ID: " + doctorId);
        }

        // 2. Buscar Página (Spring Data hace la magia)
        Page<Appointments> appointmentsPage = appointmentsRepository.findByDoctorId(doctorId, pageable);

        // 3. Mapear (Page tiene su propio .map, no necesitamos stream().collect())
        return appointmentsPage.map(appointmentMapper::toResponse);
    }

    @Override
    public AppointmentResponseDTO create(AppointmentRequestDTO request) {
        Doctors doctor = doctorsRepository.findById(request.doctorId())
                .orElseThrow(() -> new EntityNotFoundException("Doctor no encontrado con ID: " + request.doctorId()));
        Patients patient = patientsRepository.findById(request.patientId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Paciente no encontrado con ID: " + request.patientId()));

        boolean isBusy = appointmentsRepository.existsByDoctorAndDate(request.doctorId(), request.scheduledAt());
        if (isBusy) {
            throw new IllegalStateException(
                    "El doctor ya tiene una cita agendada en este horario: " + request.scheduledAt());
        }

        Appointments appointment = appointmentMapper.toEntity(request);
        appointment.setDoctor(doctor);
        appointment.setPatient(patient);

        Appointments savedAppointment = appointmentsRepository.save(appointment);
        return appointmentMapper.toResponse(savedAppointment);
    }

    @Override
    public void updateStatus(UUID id, StatusAppointments status) {

        Appointments appointment = appointmentsRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cita no encontrada"));

        if (appointment.getStatus() == StatusAppointments.COMPLETED) {
            throw new IllegalStateException("No se puede cambiar el estado de una cita ya completada.");
        }

        if (appointment.getStatus() == StatusAppointments.CANCELED && status != StatusAppointments.SCHEDULED) {
            throw new IllegalStateException("Una cita cancelada no puede reactivarse fácilmente.");
        }

        appointment.setStatus(status);
        appointmentsRepository.save(appointment);
    }

}
