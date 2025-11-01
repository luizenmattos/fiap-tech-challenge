package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class FirstNameValidation extends DomainValidation<Person> {

    public FirstNameValidation(Person entityToValidate) {
        super(entityToValidate);
    }
    
    @Override
    public void abstractValidation() {
        if(entityToValidate.getFirstName() == null){
            throw new DomainException("First name is required");        }
    }
}
