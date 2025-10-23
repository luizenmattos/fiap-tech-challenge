package com.fiap.fiap_tech_challenge.application.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.domain.exception.EmailAlreadyInUseException;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCrudPort;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;
import com.fiap.fiap_tech_challenge.application.port.outbound.AddressRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;

@Named
public class UserService implements UserCrudPort {

    private UserRepositoryPort userRepository;
    private PersonRepositoryPort personRepository;
    private AddressRepositoryPort addressRepositoryPort;

    public UserService(UserRepositoryPort userRepository, PersonRepositoryPort personRepository, AddressRepositoryPort addressRepositoryPort){
        this.userRepository = userRepository;
        this.personRepository = personRepository;
        this.addressRepositoryPort = addressRepositoryPort;
    }

    @Override
    public UserCreateOutput create(UserCreateInput userInput) {

        if (personRepository.existsByEmail(userInput.email())){
            throw new EmailAlreadyInUseException(userInput.email());
        }

        //! COMO GARANTIR QUE AMBAS INSTÂNCIAS SEJAM SALVAS?
        User user = User.newInstance(userInput.login(), userInput.password());        
        user = userRepository.save(user);

        Person person = Person.newInstance(user.getId(), userInput.firstName(), userInput.lastName(), userInput.phone(), userInput.email());
        person = personRepository.save(person);

        Address address = Address.newInstance(user.getId(), userInput.countryCode(), userInput.postalCode(), userInput.state(), userInput.city(), userInput.street(), userInput.number(), userInput.complement());
        address = addressRepositoryPort.save(address);
                
        return UserCreateOutput.fromDomain(user, person, address);
    }

    @Override
    public UserReadOutput findById(Long id) {
        Person person = personRepository.findByUserId(id).get();
        Address address = addressRepositoryPort.findByUserId(id).get();

        return UserReadOutput.newInstance(id, person, address);
    }

    @Override
    public List<UserReadOutput> findAll() {
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
    }

    @Override
    public List<UserReadOutput> findByName(String name) {
        return this.findAll().stream()
            .filter(user -> 
                name.equals(user.firstName()+ " " +user.lastName())
            )
            .toList();
    }

    @Override
    public UserUpdateOutput udpate(Long id, UserUpdateInput userInput) {
        User user = userRepository.findById(id).get();
        Person person = personRepository.findByUserId(id).get();
        Address address = addressRepositoryPort.findByUserId(id).get();

        person.updatePersonalInfo(userInput.firstName(), userInput.lastName(), userInput.phone());
        person = personRepository.save(person);

        address.updateAdress(userInput.countryCode(), userInput.postalCode(), userInput.state(), userInput.city(), userInput.street(), userInput.number(), userInput.complement());
        address = addressRepositoryPort.save(address);

        return UserUpdateOutput.newInstance(user, person, address);
    }

    @Override
    public void deleteById(Long id) {
        Person person = personRepository.findByUserId(id).get();
        person.delete();
        personRepository.save(person);

        Address address = addressRepositoryPort.findByUserId(id).get();
        address.delete();
        addressRepositoryPort.save(address);
    }
}
