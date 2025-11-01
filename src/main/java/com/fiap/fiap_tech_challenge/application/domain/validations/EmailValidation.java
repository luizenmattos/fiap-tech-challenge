package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class EmailValidation extends DomainValidation<Person> {

    public EmailValidation(Person entityToValidate) {
        super(entityToValidate);
    }

    //TODO: IMPLEMENAR VALIDACAO MELHOR
    @Override
    public void abstractValidation() {
        if(this.entityToValidate.getEmail() == null){
            throw new DomainException("Email is required");        
        }
    }
}
