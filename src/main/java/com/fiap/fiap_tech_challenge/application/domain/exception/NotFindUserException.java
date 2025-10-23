package com.fiap.fiap_tech_challenge.application.domain.exception;

public class NotFindUserException extends RuntimeException{
    public NotFindUserException(String message) {
        super(message);
    }
}
