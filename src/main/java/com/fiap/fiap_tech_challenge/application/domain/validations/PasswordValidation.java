package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

public class PasswordValidation extends DomainValidation<User> {

    public PasswordValidation(User entityToValidate) {
        super(entityToValidate);
    }

    @Override
    public void abstractValidation() {
        String password = entityToValidate.getPassword();

        if (password == null || password.isBlank()) {
            throw new DomainException("Password is required.");
        }

        if (password.length() < 8) {
            throw new DomainException("Password must be at least 8 characters long.");
        }

        if (!password.matches(".*[A-Z].*")) {
            throw new DomainException("Password must contain at least one uppercase letter.");
        }

        if (!password.matches(".*[a-z].*")) {
            throw new DomainException("Password must contain at least one lowercase letter.");
        }

        if (!password.matches(".*\\d.*")) {
            throw new DomainException("Password must contain at least one digit.");
        }

        if (!password.matches(".*[!@#$%^&*(),.?\":{}|<>].*")) {
            throw new DomainException("Password must contain at least one special character.");
        }
    }
}
