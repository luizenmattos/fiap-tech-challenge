package com.fiap.fiap_tech_challenge.application.domain.exception;

public class EmailAlreadyInUseException extends RuntimeException{
    public EmailAlreadyInUseException(String email){
        super("E-mail já cadastrado: " + email);
    }

}
