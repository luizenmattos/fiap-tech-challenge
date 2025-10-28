package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;


import jakarta.persistence.*;
import lombok.Data;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

@Data
@Entity
@Table(name = "user_table")
public class UserJpaEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String login;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

}
