package com.fiap.fiap_tech_challenge.application.port.outbound;

import java.util.List;
import java.util.Optional;

import com.fiap.fiap_tech_challenge.application.domain.Address;

public interface AddressRepositoryPort {
    
    Address save(Address address);
    
    void deleteById(Long id);

    Optional<Address> findByUserId(Long id);

    List<Address> findAll();
    
}
