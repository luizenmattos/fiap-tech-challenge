package com.fiap.fiap_tech_challenge.application.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Named;

import com.fiap.fiap_tech_challenge.application.domain.Person;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCrudPort;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateInput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;
import com.fiap.fiap_tech_challenge.application.port.outbound.PersonRepositoryPort;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;

@Named
public class UserService implements UserCrudPort {

    private UserRepositoryPort userRepository;
    private PersonRepositoryPort personRepository;

    public UserService(UserRepositoryPort userRepository, PersonRepositoryPort personRepository){
        this.userRepository = userRepository;
        this.personRepository = personRepository;
    }

    @Override
    public UserCreateOutput create(UserCreateInput userInput) {
        //! COMO GARANTIR QUE AMBAS INSTÂNCIAS SEJAM SALVAS?
        User user = User.newInstance(userInput.login(), userInput.password());        
        user = userRepository.save(user);

        Person person = Person.newInstance(user.getId(), userInput.firstName(), userInput.lastName(), userInput.phone());
        person = personRepository.save(person);
                
        return UserCreateOutput.newInstance(user, person);
    }

    @Override
    public UserReadOutput findById(Long id) {
        Person person = personRepository.findByUserId(id);

        return UserReadOutput.newInstance(id, person);
    }

    @Override
    public List<UserReadOutput> findAll() {
        List<Person> people = personRepository.findAll();

        return people.stream()
            .map(person -> UserReadOutput.newInstance(person.getUserId(), person))
            .collect(Collectors.toList());
    }
    

    @Override
    public UserUpdateOutput udpate(Long id, UserUpdateInput userInput) {
        User user = userRepository.findById(id).get();
        Person person = personRepository.findByUserId(id);

        person.updatePersonalInfo(userInput.firstName(), userInput.lastName(), userInput.phone());

        person = personRepository.save(person);

        return UserUpdateOutput.newInstance(user, person);
    }

    @Override
    public void deleteById(Long id) {
        Person person = personRepository.findByUserId(id);
        person.delete();
        
        personRepository.save(person);
    }
}
