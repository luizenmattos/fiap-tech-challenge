package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;


import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "address_table")
public class AddressJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long userId;
    
    private String countryCode;
    @Column(nullable = false)
    private String postalCode;
    @Column(nullable = false)
    private String state;
    @Column(nullable = false)
    private String city;
    @Column(nullable = false)
    private String street;
    @Column(nullable = false)
    private String number;
    private String complement;

    @Column(nullable = false)
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
