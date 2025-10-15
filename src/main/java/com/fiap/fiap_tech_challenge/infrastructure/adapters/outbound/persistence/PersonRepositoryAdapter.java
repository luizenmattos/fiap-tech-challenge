package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.PersonJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.PersonJpaRepository;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonRepositoryAdapter implements PersonRepositoryPort {

    private final PersonJpaRepository personJpaRepository;

    public PersonRepositoryAdapter(PersonJpaRepository personJpaRepository){
        this.personJpaRepository = personJpaRepository;
    }

    @Override
    public Person save(Person person) {
        var entity = toEntity(person);
        var saved = personJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Person findByUserId(Long userId) {
        var entity = personJpaRepository.findByUserId(userId);
        return entity != null ? toDomain(entity) : null;
    }

    @Override
    public List<Person> findAll() {
        return personJpaRepository.findAll()
                .stream()
                .map(this::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        personJpaRepository.deleteById(id);
    }


    PersonJpaEntity toEntity(Person person) {
        var entity = new PersonJpaEntity();
        entity.setUserId(person.getUserId());
        entity.setFirstName(person.getFirstName());
        entity.setLastName(person.getLastName());
        entity.setPhone(person.getPhone());
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
        person.setCreatedAt(entity.getCreatedAt());
        person.setUpdatedAt(entity.getUpdatedAt());
        person.setDeletedAt(entity.getDeletedAt());
        return person;
    }
}
