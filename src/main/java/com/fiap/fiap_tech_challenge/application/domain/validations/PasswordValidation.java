package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class PasswordValidation extends DomainValidation<User> {

    public PasswordValidation(User entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        if(entityToValidate.getPassword() == null){
            throw new DomainException("Password is required");        }
    }
}
