package com.fiap.fiap_tech_challenge.application.domain.exception;

public class InvalidOrExpiredToken extends RuntimeException{
    public InvalidOrExpiredToken(String msg){
        super(msg);
    }
}
