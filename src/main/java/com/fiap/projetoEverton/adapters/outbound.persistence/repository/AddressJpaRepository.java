package com.fiap.techchallenge1_G13.adapters.outbound.persistence.repository;

import com.fiap.techchallenge1_G13.domain.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressJpaRepository extends JpaRepository<Address, Long> {
}
