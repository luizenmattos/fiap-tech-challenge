package com.fiap.projetoEverton.adapters.outbound.persistence.repository;

import com.fiap.projetoEverton.adapters.outbound.persistence.entity.AddressJpaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressJpaRepository extends JpaRepository<AddressJpaEntity, Long> {
}
