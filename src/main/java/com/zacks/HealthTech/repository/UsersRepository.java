package com.zacks.HealthTech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import com.zacks.HealthTech.model.Users;

public interface UsersRepository extends JpaRepository<Users, UUID> {
    boolean existsByEmail(String email);
}
