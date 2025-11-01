package com.fiap.fiap_tech_challenge.application.domain.validations;

import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.UserRole;
import com.fiap.fiap_tech_challenge.application.domain.exception.NotFindUserException;

public class Validations {

    public static void validateAdmin(User loggedUser){
        if(loggedUser.getRole() != UserRole.ADMIN){
            throw new NotFindUserException("User not authorized to access this information");
        }
    };

    public static void validateAdminOrUser(User loggedUser, Long userIdToUpdate){
        if((loggedUser.getRole() != UserRole.ADMIN) && (loggedUser.getRole() == UserRole.USER && loggedUser.getId() == userIdToUpdate)){
            throw new NotFindUserException("User not authorized to access this information");
        }
    };

    public static void validateLogin(User loggingUser, String password){
        if(!loggingUser.getPassword().equals(password)){
            throw  new NotFindUserException("Password incorrect");
        }
    };

    public static void validateBeforeUserCreate(User user, Person person, Address address){
        DomainValidation<?> chainValidations = new EmailValidation(person);
        chainValidations.setNext(new FirstNameValidation(person))
            .setNext(new PasswordValidation(user))
            .setNext(new PostalCodeValidation(address))
            .setNext(new RoleValidation(user));
        
        chainValidations.validate();
    }

    public static void validateBeforeUserUpdate(User user, Person person, Address address){
        DomainValidation<?> chainValidations = new EmailValidation(person);
        chainValidations.setNext(new FirstNameValidation(person))
            .setNext(new PostalCodeValidation(address))
            .setNext(new RoleValidation(user));
        
        chainValidations.validate();
    }
    
}
