package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.AddressJpaEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, Long> {
    Optional<AddressJpaEntity> findByUserId(Long userId);
}
