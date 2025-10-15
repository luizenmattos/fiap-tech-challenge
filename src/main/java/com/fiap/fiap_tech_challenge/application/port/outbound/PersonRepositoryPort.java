package com.fiap.fiap_tech_challenge.application.port.outbound;

import java.util.List;

import com.fiap.fiap_tech_challenge.application.domain.Person;

public interface PersonRepositoryPort {
    
    Person save(Person person);
    
    Person findByUserId(Long id);

    List<Person> findAll();
    
    void deleteById(Long id);
}
