package com.zacks.HealthTech.model;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Table;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Entity
@Table(name = "doctors")
public class Doctors extends BaseEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "users_id", referencedColumnName = "id")
    private Users user;

    @ManyToOne
    @JoinColumn(name = "specialty_id")
    private Specialties specialty;

    @Column(name = "license_number", nullable = false, unique = true)
    private String licenseNumber;

    private String firstName;
    private String lastName;

    @Column(name = "consulting_room")
    private String consultingRoom;

}
