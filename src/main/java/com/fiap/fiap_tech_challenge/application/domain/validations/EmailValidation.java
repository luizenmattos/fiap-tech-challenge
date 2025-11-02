package com.fiap.fiap_tech_challenge.application.domain.validations;

import java.util.regex.Pattern;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;

public class EmailValidation extends DomainValidation<Person> {

    private final PersonRepositoryPort personRepository;

    private static final Pattern EMAIL_PATTERN =
        Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", Pattern.CASE_INSENSITIVE);

    public EmailValidation(Person entityToValidate, PersonRepositoryPort personRepository) {
        super(entityToValidate);
        this.personRepository = personRepository;
    }

    @Override
    protected void abstractValidation() {
        String email = entityToValidate.getEmail();

        if (email == null || email.isBlank()) {
            throw new DomainException("Email is required.");
        }

        if (email.length() > 254) {
            throw new DomainException("Email must not exceed 254 characters.");
        }

        if (!EMAIL_PATTERN.matcher(email).matches()) {
            throw new DomainException("Email format is invalid.");
        }

        personRepository.findByEmail(email).ifPresent(existingPerson -> {
            if (!existingPerson.getId().equals(entityToValidate.getId())) {
                throw new DomainException("Email is already registered.");
            }
        });
    }
}
