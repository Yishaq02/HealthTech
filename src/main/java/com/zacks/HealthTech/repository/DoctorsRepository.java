package com.zacks.HealthTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.zacks.HealthTech.model.Doctors;

public interface DoctorsRepository extends JpaRepository<Doctors, UUID> {

}
