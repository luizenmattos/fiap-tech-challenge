package com.fiap.fiap_tech_challenge.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.UserRole;
import com.fiap.fiap_tech_challenge.application.domain.exception.EntityNotFoundException;
import com.fiap.fiap_tech_challenge.application.domain.validations.Validations;
import com.fiap.fiap_tech_challenge.application.port.inbound.*;
import com.fiap.fiap_tech_challenge.application.port.outbound.AddressRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.config.JwtUtil;

import lombok.extern.slf4j.Slf4j;

@Named
@Slf4j
public class UserService implements UserCrudPort {

    private UserRepositoryPort userRepository;
    private PersonRepositoryPort personRepository;
    private AddressRepositoryPort addressRepositoryPort;
    private final JwtUtil jwtUtil;

    public UserService(UserRepositoryPort userRepository, PersonRepositoryPort personRepository, AddressRepositoryPort addressRepositoryPort, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.addressRepositoryPort = addressRepositoryPort;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserCreateOutput create(String token,UserCreateInput userInput) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdmin(loggedUser);

        User user = User.newInstance(userInput.login(), userInput.password(), UserRole.fromExternal(userInput.role()));
        Person person = Person.newInstance(user.getId(), userInput.firstName(), userInput.lastName(), userInput.phone(), userInput.email());
        Address address = Address.newInstance(user.getId(),userInput.countryCode(), userInput.postalCode(), userInput.state(),userInput.city(), userInput.street(), userInput.number(), userInput.complement());

        Validations.validateBeforeUserCreate(user, person, address);

        user = userRepository.save(user)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        
        person.setUserId(user.getId());
        person = personRepository.save(person)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        
        address.setUserId(user.getId());
        address = addressRepositoryPort.save(address)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return UserCreateOutput.fromDomain(user, person, address);
    }

    @Override
    public UserReadOutput findById(String token,Long id) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdminOrUser(loggedUser, id);

        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return UserReadOutput.newInstance(user, person, address);
    }


    @Override
    public List<UserReadOutput> findAll(String token) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdmin(loggedUser);

        List<User> users = userRepository.findAll();
        List<Person> people = personRepository.findAll();
        List<Address> addresses = addressRepositoryPort.findAll();

        Map<Long, Person> peopleMap = new HashMap<>();
        for (Person person : people) {
            peopleMap.put(person.getUserId(), person);
        }

        Map<Long, Address> addressMap = new HashMap<>();
        for (Address address : addresses) {
            addressMap.put(address.getUserId(), address);
        }

        List<UserReadOutput> result = new ArrayList<>();
        for (User user : users) {
            Person person = peopleMap.get(user.getId());
            Address address = addressMap.get(user.getId());
            result.add(UserReadOutput.newInstance(user, person, address));
        }

        return result;
    }

    @Override
    public List<UserReadOutput> findByName(String token,String name) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdmin(loggedUser);

        List<UserReadOutput> userReadOutputs = this.findAll(token);

        return userReadOutputs.stream()
            .filter(it->name.equals(it.firstName() + ' ' + it.lastName()))
            .toList();
    }

    @Override
    public UserUpdateOutput udpate(String token, Long id, UserUpdateInput userInput) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdminOrUser(loggedUser, id);

        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        person.updatePersonalInfo(userInput.firstName(), userInput.lastName(), userInput.phone(), userInput.email());
        address.updateAdress(userInput.countryCode(), userInput.postalCode(), userInput.state(), userInput.city(), userInput.street(), userInput.number(), userInput.complement());
        
        Validations.validateBeforeUserUpdate(user, person, address);

        person = personRepository.save(person)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        address = addressRepositoryPort.save(address)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));

        return UserUpdateOutput.newInstance(user, person, address);
    }

    @Override
    public void deleteById(String token,Long id) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        Validations.validateAdminOrUser(loggedUser, id);

        User user = userRepository.findById(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        user.delete();
        userRepository.save(user);

        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        person.delete();
        personRepository.save(person);

        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        address.delete();
        addressRepositoryPort.save(address);
    }

    @Override
    public String login(String login, String password) {
        log.info("Loggin with \"" + login + "\"...");

        User loggingUser = userRepository.findByLogin(login)
            .orElseThrow(() -> new EntityNotFoundException("User not found."));
        
        Validations.validateLogin(loggingUser, password);
        
        try {
            return jwtUtil.generateToken(loggingUser);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void changePassword(String token, UserUpdatePasswordInput password) {
        User loggedUser = jwtUtil.validateAndGetUsername(token);
        userRepository.changePassword(loggedUser.getId(),password.newPassword());
    }
}
