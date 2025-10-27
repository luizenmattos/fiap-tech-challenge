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
import com.fiap.fiap_tech_challenge.application.domain.exception.DataNotFoundException;
import com.fiap.fiap_tech_challenge.application.domain.exception.DomainException;
import com.fiap.fiap_tech_challenge.application.domain.exception.NotFindUserException;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCrudPort;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;
import com.fiap.fiap_tech_challenge.application.port.outbound.AddressRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.config.JwtUtil;

import lombok.extern.slf4j.Slf4j;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Named
@Slf4j
public class UserService implements UserCrudPort {

    private UserRepositoryPort userRepository;
    private PersonRepositoryPort personRepository;
    private AddressRepositoryPort addressRepositoryPort;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepositoryPort userRepository, PersonRepositoryPort personRepository, AddressRepositoryPort addressRepositoryPort, JwtUtil jwtUtil){
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.addressRepositoryPort = addressRepositoryPort;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public UserCreateOutput create(UserCreateInput userInput) {
        log.info("Creating user...");

        if (personRepository.existsByEmail(userInput.email())){
            throw new DomainException("Email already exists: " + userInput.email());
        }

        UserRole role = UserRole.fromExternal(userInput.role());

        User user = User.newInstance(userInput.login(), userInput.password(), role);
        user = userRepository.save(user)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        Person person = Person.newInstance(user.getId(), userInput.firstName(), userInput.lastName(), userInput.phone(), userInput.email());
        person = personRepository.save(person)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        var address = Address.newInstance(
                user.getId(),
                userInput.countryCode(), userInput.postalCode(), userInput.state(),
                userInput.city(), userInput.street(), userInput.number(), userInput.complement()
        );
        address = addressRepositoryPort.save(address)
                .orElseThrow(() -> new DataNotFoundException("User not found."));
                
        return UserCreateOutput.fromDomain(user, person, address);
    }

    @Override
    public UserReadOutput findById(Long id) {
        log.info("Getting user by id...");

        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        
        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        return UserReadOutput.newInstance(id, person, address);
    }

    @Override
    public List<UserReadOutput> findAll(String token) {
        log.info("Getting all users...");

        User u = jwtUtil.validateAndGetUsername(token);

        if(u.getRole() == UserRole.USER){
            List<Person> people = personRepository.findAll();
            List<Address> addresses = addressRepositoryPort.findAll();

            Map<Long, Address> addressMap = new HashMap<>();
            for (Address address : addresses) {
                addressMap.put(address.getUserId(), address);
            }

            List<UserReadOutput> result = new ArrayList<>();
            for (Person person : people) {
                Address address = addressMap.get(person.getUserId());
                result.add(UserReadOutput.newInstance(person.getUserId(), person, address));
            }

            return result;
        }else {
            throw new NotFindUserException("User not authorized to access this information");
        }
    }

    @Override
    public List<UserReadOutput> findByName(String name) {
        log.info("Getting user by name...");
        return null;

           /*     this.findAll().stream()
            .filter(user -> 
                name.equals(user.firstName()+ " " +user.lastName())
            )
            .toList();*/
    }

    @Override
    public UserUpdateOutput udpate(Long id, UserUpdateInput userInput) {
        log.info("Updating user...");

        User user = userRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        
        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        
        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        person.updatePersonalInfo(userInput.firstName(), userInput.lastName(), userInput.phone(), userInput.email());
        person = personRepository.save(person)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        address.updateAdress(userInput.countryCode(), userInput.postalCode(), userInput.state(), userInput.city(), userInput.street(), userInput.number(), userInput.complement());
        address = addressRepositoryPort.save(address)
            .orElseThrow(() -> new DataNotFoundException("User not found."));

        return UserUpdateOutput.newInstance(user, person, address);
    }

    @Override
    public void deleteById(Long id) {
        log.info("Deleting user...");

        User user = userRepository.findById(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        user.delete();
        userRepository.save(user);

        Person person = personRepository.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        person.delete();
        personRepository.save(person);

        Address address = addressRepositoryPort.findByUserId(id)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        address.delete();
        addressRepositoryPort.save(address);
    }

    @Override
    public String login(String login, String password) {
        log.info("Loggin with \"" + login + "\"...");

        User u = userRepository.findByLogin(login)
            .orElseThrow(() -> new DataNotFoundException("User not found."));
        
        if(u.getPassword().equals(password)){
            try {
                return jwtUtil.generateToken(u);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }else {
            throw  new NotFindUserException("Password incorrect");
        }
    }
}
