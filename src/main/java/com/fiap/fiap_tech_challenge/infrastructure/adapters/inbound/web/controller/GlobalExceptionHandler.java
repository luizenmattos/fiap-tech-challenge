package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fiap.fiap_tech_challenge.application.domain.exception.EntityNotFoundException;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;
import com.fiap.fiap_tech_challenge.application.domain.exception.NotFindUserException;
import com.fiap.fiap_tech_challenge.application.domain.exception.InvalidOrExpiredToken;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ProblemDetail handleEntityNotFoundException(EntityNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problemDetail.setTitle("Resource not found");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(NotFindUserException.class)
    public ProblemDetail handleNotFindUserException(NotFindUserException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.UNAUTHORIZED,
            ex.getMessage()
        );

        problemDetail.setTitle("User not allowed for this operation");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDomainException(DomainException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            ex.getErrors()
        );

        problemDetail.setTitle(ex.getMessage());
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(InvalidOrExpiredToken.class)
    public ProblemDetail handleInvalidOrExpiredToken(InvalidOrExpiredToken ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.UNAUTHORIZED,
            ex.getMessage()
        );

        problemDetail.setTitle("Operation not allowed");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleGenericException(Exception ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.INTERNAL_SERVER_ERROR,
            ex.getMessage()
        );

        problemDetail.setTitle("Internal serve error");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }
}
