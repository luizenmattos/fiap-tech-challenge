package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class RoleValidation extends DomainValidation<User> {

    public RoleValidation(User entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        if(entityToValidate.getRole() == null){
            throw new DomainException("Role is required");        }
    }

}
