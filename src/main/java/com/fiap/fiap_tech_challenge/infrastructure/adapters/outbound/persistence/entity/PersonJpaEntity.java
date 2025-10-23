package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "people")
public class PersonJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long userId;

    private String firstName;
    private String lastName;
    private String phone;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
