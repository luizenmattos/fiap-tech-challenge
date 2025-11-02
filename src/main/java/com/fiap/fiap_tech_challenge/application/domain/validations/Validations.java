package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.UserRole;
import com.fiap.fiap_tech_challenge.application.domain.exception.NotFindUserException;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;

public class Validations {

    public static void validateOwner(User loggedUser){
        if(loggedUser.getRole() != UserRole.OWNER){
            throw new NotFindUserException("User not authorized to access this information");
        }
    };

    public static void validateOwnerOrClient(User loggedUser, Long userIdToUpdate){
        if((loggedUser.getRole() != UserRole.OWNER) && (loggedUser.getRole() == UserRole.CLIENT && loggedUser.getId() == userIdToUpdate)){
            throw new NotFindUserException("User not authorized to access this information");
        }
    };

    public static void validateLogin(User loggingUser, String password){
        if(!loggingUser.getPassword().equals(password)){
            throw  new NotFindUserException("Password incorrect");
        }
    };

    public static void validateBeforeUserCreate(User user, Person person, Address address, PersonRepositoryPort personRepository){
        DomainValidation<?> chainValidations = new EmailValidation(person, personRepository);
        chainValidations.setNext(new FirstNameValidation(person))
            .setNext(new PasswordValidation(user))
            .setNext(new PostalCodeValidation(address))
            .setNext(new RoleValidation(user));
        
        chainValidations.validate();
    }

    public static void validateBeforeUserUpdate(User user, Person person, Address address, PersonRepositoryPort personRepository){
        DomainValidation<?> chainValidations = new EmailValidation(person, personRepository);
        chainValidations.setNext(new FirstNameValidation(person))
            .setNext(new PostalCodeValidation(address))
            .setNext(new RoleValidation(user));
        
        chainValidations.validate();
    }
    
}
