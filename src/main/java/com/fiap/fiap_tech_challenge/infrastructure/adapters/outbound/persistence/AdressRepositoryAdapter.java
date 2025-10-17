package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.port.outbound.AddressRepositoryPort;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.AddressJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.PersonJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.AddressJpaRepository;

@Component
public class AdressRepositoryAdapter implements AddressRepositoryPort {

    private final AddressJpaRepository addressJpaRepository;

    public AdressRepositoryAdapter(AddressJpaRepository addressJpaRepository){
        this.addressJpaRepository = addressJpaRepository;
    }

    @Override
    public Address save(Address address) {
        var entity = toEntity(address);
        var saved = addressJpaRepository.save(entity);
        return  toDomain(saved);
    }

    @Override
    public void deleteById(Long id) {
        addressJpaRepository.deleteById(id);
    }

    @Override
    public Optional<Address> findByUserId(Long id) {
        Optional<AddressJpaEntity> entity = addressJpaRepository.findByUserId(id);
        return entity.map(this::toDomain);
    }

    @Override
    public List<Address> findAll() {
        return addressJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    AddressJpaEntity toEntity(Address address) {
        var entity = new AddressJpaEntity();
        entity.setUserId(address.getUserId());
        entity.setCountryCode(address.getCountryCode());
        entity.setPostalCode(address.getPostalCode());
        entity.setState(address.getState());
        entity.setCity(address.getCity());
        entity.setStreet(address.getStreet());
        entity.setNumber(address.getNumber());
        entity.setComplement(address.getComplement());
        entity.setCreatedAt(address.getCreatedAt());
        entity.setUpdatedAt(address.getUpdatedAt());
        entity.setDeletedAt(address.getDeletedAt());
        return entity;
    }

    Address toDomain(AddressJpaEntity entity) {
        var address = new Address();
        address.setUserId(entity.getUserId());
        address.setCountryCode(entity.getCountryCode());
        address.setPostalCode(entity.getPostalCode());
        address.setState(entity.getState());
        address.setCity(entity.getCity());
        address.setStreet(entity.getStreet());
        address.setNumber(entity.getNumber());
        address.setComplement(entity.getComplement());
        address.setCreatedAt(entity.getCreatedAt());
        address.setUpdatedAt(entity.getUpdatedAt());
        address.setDeletedAt(entity.getDeletedAt());
        return address;
    }

}
