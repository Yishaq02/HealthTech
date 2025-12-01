package com.zacks.HealthTech.model;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

import java.time.LocalDate;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "patients")
public class Patients extends BaseEntity {

    @Column(unique = true, nullable = false)
    private String name;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false)
    private LocalDate dob;

    private String phone;

    @Column(nullable = false)
    private String insurance;

    @Column(name = "is_active")
    @Builder.Default
    private Boolean isActive = true;

}
