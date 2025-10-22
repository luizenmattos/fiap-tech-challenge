package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.UserJpaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
// import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends JpaRepository<UserJpaEntity, Long> {

    // Optional<UserDetails> findByLogin(String login);

}
