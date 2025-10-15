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

    private Long userId;
    private String firstName;
    private String lastName;
    private String phone;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;
    public Object map(Object object) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'map'");
    }
}
