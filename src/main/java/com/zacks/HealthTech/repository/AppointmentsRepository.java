package com.zacks.HealthTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.UUID;
import com.zacks.HealthTech.model.Appointments;

public interface AppointmentsRepository extends JpaRepository<Appointments, UUID> {

    /**
     * Checks if a doctor has a conflicting appointment at the specified time.
     * Only considers non-canceled appointments to prevent overbooking.
     * 
     * @param doctorId the doctor's UUID
     * @param date     the exact scheduled time to check
     * @return true if doctor is busy, false if available
     */
    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM Appointments a " +
            "WHERE a.doctor.id = :doctorId " +
            "AND a.scheduledAt = :date " +
            "AND a.status <> 'CANCELED'")
    boolean existsByDoctorAndDate(@Param("doctorId") UUID doctorId, @Param("date") LocalDateTime date);

    /**
     * Retrieves all appointments for a specific doctor.
     * Results are ordered by scheduled time in descending order (most recent
     * first).
     * 
     * @param doctorId the doctor's UUID
     * @return list of appointments
     * @throws jakarta.persistence.EntityNotFoundException if doctor does not exist
     */
    Page<Appointments> findByDoctorId(UUID doctorId, Pageable pageable);

}
