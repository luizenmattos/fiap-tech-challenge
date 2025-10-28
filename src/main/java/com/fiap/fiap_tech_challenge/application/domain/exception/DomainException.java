package com.fiap.fiap_tech_challenge.application.domain.exception;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DomainException extends RuntimeException{
    public DomainException(String message){
        super(message);
        log.info(message);
    }

}
