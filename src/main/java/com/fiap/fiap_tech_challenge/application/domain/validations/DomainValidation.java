package com.fiap.fiap_tech_challenge.application.domain.validations;

import java.util.ArrayList;
import java.util.List;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;

import lombok.extern.slf4j.Slf4j;

public abstract class DomainValidation<T> {

    protected T entityToValidate;
    protected List<String> errors = new ArrayList<>();
    protected DomainValidation<?> nextValidation;

    public DomainValidation(T entityToValidate) {
        this.entityToValidate = entityToValidate;
    }

    public DomainValidation<?> setNext(DomainValidation<?> nextValidation) {
        this.nextValidation = nextValidation;
        nextValidation.errors = this.errors;
        return nextValidation;
    }

    public void validate() {
        try {
            abstractValidation();
        } catch (DomainException e) {
            errors.add(e.getMessage());
        }

        if (nextValidation != null) {
            nextValidation.validate();
        }

        if (!errors.isEmpty()) {
            throw new DomainException(errors);
        }
    }

    protected abstract void abstractValidation();
}
