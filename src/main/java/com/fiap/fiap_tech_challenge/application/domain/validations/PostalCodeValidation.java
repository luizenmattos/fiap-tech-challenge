package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class PostalCodeValidation extends DomainValidation<Address> {

    public PostalCodeValidation(Address entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        if(entityToValidate.getPostalCode() == null){
            throw new DomainException("Postal code is required");        }
    }
}
