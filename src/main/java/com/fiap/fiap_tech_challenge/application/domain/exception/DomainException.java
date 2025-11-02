package com.fiap.fiap_tech_challenge.application.domain.exception;

import java.util.List;

public class DomainException extends RuntimeException{

    private List<String> errors;
    
    public DomainException(String message){
        super(message);
    }
    
    public DomainException(List<String> errors){
        super("Validation errors");
        this.errors = errors;
    }

    public String getErrors() {
        return errors.toString();
    }
}
