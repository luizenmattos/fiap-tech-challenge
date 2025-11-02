package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class LoginValidation extends DomainValidation<User> {

    public LoginValidation(User entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        if(entityToValidate.getLogin() == null || entityToValidate.getLogin().isBlank()){
            throw new DomainException("Login is required");        }
    }
}
