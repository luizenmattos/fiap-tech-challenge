package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.PersonJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.PersonJpaRepository;

import lombok.extern.slf4j.Slf4j;

import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.domain.Person;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@Slf4j
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository personJpaRepository;

    public PersonRepositoryAdapter(PersonJpaRepository personJpaRepository){
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Optional<Person> save(Person person) {
        var entity = toEntity(person);
        var saved = personJpaRepository.save(entity);
        return  Optional.of(toDomain(saved));
    }

    @Override
    public Optional<Person> findByUserId(Long userId) {
        Optional<PersonJpaEntity> entity = personJpaRepository.findByUserIdAndDeletedAtIsNull(userId);
        return entity.map(this::toDomain);
    }

    @Override
    public List<Person> searchByName(String name) {
        return personJpaRepository.searchByName(name)
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Person> findAll() {
        return personJpaRepository.findAllByDeletedAtIsNull()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

   @Override
    public boolean existsByEmail(String email) {
        log.info("Verificando existência do e-mail: {}", email);
        return personJpaRepository.findByEmailAndDeletedAtIsNull(email).isPresent();
    }

    PersonJpaEntity toEntity(Person person) {
        var entity = new PersonJpaEntity();
        entity.setId(person.getId());
        entity.setUserId(person.getUserId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setPhone(person.getPhone());
        entity.setEmail(person.getEmail());
        entity.setCreatedAt(person.getCreatedAt());
        entity.setUpdatedAt(person.getUpdatedAt());
        entity.setDeletedAt(person.getDeletedAt());
        return entity;
    }

    Person toDomain(PersonJpaEntity entity) {
        var person = new Person();
        person.setId(entity.getId());
        person.setUserId(entity.getUserId());
        person.setFirstName(entity.getFirstName());
        person.setLastName(entity.getLastName());
        person.setPhone(entity.getPhone());
        person.setEmail(entity.getEmail());
        person.setCreatedAt(entity.getCreatedAt());
        person.setUpdatedAt(entity.getUpdatedAt());
        person.setDeletedAt(entity.getDeletedAt());
        return person;
    }
}
