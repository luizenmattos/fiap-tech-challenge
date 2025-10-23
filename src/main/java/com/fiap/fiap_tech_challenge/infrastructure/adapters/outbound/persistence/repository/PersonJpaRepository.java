package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.PersonJpaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonJpaRepository extends JpaRepository<PersonJpaEntity, Long> {

    Optional<PersonJpaEntity> findByUserId(Long userId);

    boolean existsByEmail(String email);
}
