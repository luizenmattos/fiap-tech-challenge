package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;


import java.time.Instant;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "addresses")
public class AddressJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private Long userId;
    
    private String countryCode;
    private String postalCode;
    private String state;
    private String city;
    private String street;
    private String number;
    private String complement;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
}
