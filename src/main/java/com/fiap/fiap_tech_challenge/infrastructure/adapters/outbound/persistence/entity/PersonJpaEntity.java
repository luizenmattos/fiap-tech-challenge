package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Data
@Entity
@Table(name = "person_table")
public class PersonJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;

    @Column(nullable = false)
    private String email;

    private String firstName;
    private String lastName;
    private String phone;

    @Column(nullable = false)
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
