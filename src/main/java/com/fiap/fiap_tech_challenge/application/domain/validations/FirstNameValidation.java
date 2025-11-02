package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class FirstNameValidation extends DomainValidation<Person> {

    public FirstNameValidation(Person entityToValidate) {
        super(entityToValidate);
    }
    
    @Override
    public void abstractValidation() {
        String firstName = entityToValidate.getFirstName();

        if (firstName == null || firstName.isBlank()) {
            throw new DomainException("First name is required");
        }

        if (firstName.length() < 2) {
            throw new DomainException("First name must have at least 2 characters");
        }

        if (!firstName.matches("^[A-Za-zÀ-ÖØ-öø-ÿ'\\-\\s]+$")) {
            throw new DomainException("First name contains invalid characters");
        }
    }
}
