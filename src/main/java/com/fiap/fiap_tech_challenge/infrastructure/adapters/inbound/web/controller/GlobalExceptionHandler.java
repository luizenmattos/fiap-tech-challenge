package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.fiap.fiap_tech_challenge.application.domain.exception.DataNotFoundException;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;
import com.fiap.fiap_tech_challenge.application.domain.exception.NotFindUserException;
import com.fiap.fiap_tech_challenge.application.domain.exception.InvalidOrExpiredToken;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataNotFoundException.class)
    public ProblemDetail handleDataNotFoundException(DataNotFoundException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.NOT_FOUND,
            ex.getMessage()
        );

        problemDetail.setTitle("Resource not found");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(NotFindUserException.class)
    public ProblemDetail handleDataNotFoundException(NotFindUserException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.UNAUTHORIZED,
            ex.getMessage()
        );

        problemDetail.setTitle("User not allowed for this operation");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(DomainException.class)
    public ProblemDetail handleDataNotFoundException(DomainException ex) {
        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
            HttpStatus.BAD_REQUEST,
            ex.getMessage()
        );

        problemDetail.setTitle("Operation not allowed");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }

    @ExceptionHandler(InvalidOrExpiredToken.class)
    public ProblemDetail handleDataNotFoundException(InvalidOrExpiredToken ex) {
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

        problemDetail.setTitle("Intermal serve error");
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }
}
