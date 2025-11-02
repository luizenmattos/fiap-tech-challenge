package com.fiap.fiap_tech_challenge.application.port.outbound;

import java.util.List;
import java.util.Optional;

import com.fiap.fiap_tech_challenge.application.domain.Person;

public interface PersonRepositoryPort {
    
    Optional<Person> save(Person person);
    
    Optional<Person> findByUserId(Long id);

    List<Person> searchByName(String name);

    List<Person> findAll();

    Optional<Person> findByEmail(String email);
    
}
