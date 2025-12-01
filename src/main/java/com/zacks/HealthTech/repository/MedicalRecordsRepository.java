package com.zacks.HealthTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.zacks.HealthTech.model.MedicalRecords;

public interface MedicalRecordsRepository extends JpaRepository<MedicalRecords, UUID> {

}
