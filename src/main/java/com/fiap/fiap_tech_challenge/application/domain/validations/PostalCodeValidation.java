package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class PostalCodeValidation extends DomainValidation<Address> {

    public PostalCodeValidation(Address entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        String postalCode = entityToValidate.getPostalCode();

        if (postalCode == null || postalCode.isBlank()) {
            throw new DomainException("Postal code is required");
        }

        postalCode = postalCode.trim();

        if (!postalCode.matches("^\\d{5}-?\\d{3}$")) {
            throw new DomainException("Invalid postal code format. Expected format: 12345-678");
        }
    }
}
