package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;


import lombok.Data;

import jakarta.persistence.*;

@Data
@Entity
@Table(name = "logins")
public class LoginJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String token;
    private String username;






}
