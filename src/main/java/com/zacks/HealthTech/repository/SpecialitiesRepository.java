package com.zacks.HealthTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.zacks.HealthTech.model.Specialties;

public interface SpecialitiesRepository extends JpaRepository<Specialties, UUID> {

}
