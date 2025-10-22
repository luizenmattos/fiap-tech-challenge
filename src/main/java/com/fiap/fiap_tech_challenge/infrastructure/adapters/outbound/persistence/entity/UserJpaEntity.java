package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity;


import com.fiap.fiap_tech_challenge.application.domain.UserRole;
import jakarta.persistence.*;
import lombok.Data;
// import org.springframework.security.core.GrantedAuthority;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;

@Data
@Entity
@Table(name = "users")
public class UserJpaEntity /*implements UserDetails*/ {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String login;
    private String password;
    private String role;

    private Instant createdAt;
    private Instant updatedAt;
    private Instant deletedAt;

    //Metodos do UserDetails
    // @Override
    // public Collection<? extends GrantedAuthority> getAuthorities() {
    //     if(role == UserRole.ADMIN)
    //         return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"),new SimpleGrantedAuthority("ROLE_USER"));
    //     else
    //         return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    // }


    // @Override
    // public boolean isAccountNonExpired() {
    //     return UserDetails.super.isAccountNonExpired();
    // }

    // @Override
    // public boolean isAccountNonLocked() {
    //     return UserDetails.super.isAccountNonLocked();
    // }

    // @Override
    // public boolean isCredentialsNonExpired() {
    //     return UserDetails.super.isCredentialsNonExpired();
    // }

    // @Override
    // public boolean isEnabled() {
    //     return UserDetails.super.isEnabled();
    // }

}
