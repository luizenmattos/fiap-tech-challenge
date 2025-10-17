package com.fiap.fiap_tech_challenge.application.port.outbound;

import java.util.List;
import java.util.Optional;

import com.fiap.fiap_tech_challenge.application.domain.Person;

public interface PersonRepositoryPort {
    
    Person save(Person person);
    
    void deleteById(Long id);

    Optional<Person> findByUserId(Long id);

    List<Person> findAll();
    
}
