package com.zacks.HealthTech.service;

import java.util.UUID;
import org.springframework.stereotype.Service;
import com.zacks.HealthTech.dto.request.MedicalRecordRequestDTO;
import com.zacks.HealthTech.dto.response.MedicalRecordResponseDTO;
import com.zacks.HealthTech.enums.StatusAppointments;
import com.zacks.HealthTech.repository.MedicalRecordsRepository;
import com.zacks.HealthTech.repository.AppointmentsRepository;
import com.zacks.HealthTech.mapper.MedicalRecordMapper;
import com.zacks.HealthTech.model.Appointments;
import com.zacks.HealthTech.model.MedicalRecords;
import jakarta.persistence.EntityNotFoundException;

@Service
public class MedicalRecordService implements IMedicalRecordService {

    private final MedicalRecordsRepository medicalRecordsRepository;
    private final MedicalRecordMapper medicalRecordMapper;
    private final AppointmentsRepository appointmentsRepository;

    public MedicalRecordService(MedicalRecordsRepository medicalRecordsRepository,
            MedicalRecordMapper medicalRecordMapper, AppointmentsRepository appointmentsRepository) {
        this.medicalRecordsRepository = medicalRecordsRepository;
        this.medicalRecordMapper = medicalRecordMapper;
        this.appointmentsRepository = appointmentsRepository;
    }

    @Override
    public MedicalRecordResponseDTO findById(UUID id) {
        return medicalRecordsRepository.findById(id)
                .map(medicalRecordMapper::toResponse)
                .orElseThrow(() -> new EntityNotFoundException("Historial no encontrado con ID: " + id));
    }

    @Override
    public MedicalRecordResponseDTO create(MedicalRecordRequestDTO request) {

        Appointments appointment = appointmentsRepository.findById(request.appointmentsId())
                .orElseThrow(
                        () -> new EntityNotFoundException("Cita no encontrada con ID: " + request.appointmentsId()));

        if (appointment.getStatus() == StatusAppointments.CANCELED) {
            throw new IllegalStateException("No se puede crear historial para una cita cancelada.");
        }

        MedicalRecords medicalRecord = medicalRecordMapper.toEntity(request);
        medicalRecord.setAppointment(appointment);

        appointment.setStatus(StatusAppointments.COMPLETED);
        appointmentsRepository.save(appointment);

        MedicalRecords saved = medicalRecordsRepository.save(medicalRecord);
        return medicalRecordMapper.toResponse(saved);
    }

    @Override
    public MedicalRecordResponseDTO update(UUID id, MedicalRecordRequestDTO request) {
        return null;
    }

    @Override
    public void delete(UUID id) {
    }

}
