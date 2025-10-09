package com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence;

// import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.LoginJpaRepository;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.AddressJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.entity.UserJpaEntity;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.AddressJpaRepository;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.UserJpaRepository;
import com.fiap.fiap_tech_challenge.application.port.outbound.UserRepositoryPort;
import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserPersistenceAdapter implements UserRepositoryPort {

    private final UserJpaRepository userJpaRepository;
    private final AddressJpaRepository addressJpaRepository;
    //private final LoginJpaRepository loginJpaRepository;

    public UserPersistenceAdapter(UserJpaRepository userJpaRepository, AddressJpaRepository addressJpaRepository){
        this.userJpaRepository = userJpaRepository;
        this.addressJpaRepository = addressJpaRepository;
        //this.loginJpaRepository = loginJpaRepository;
    }

    @Override
    public User save(User user) {
        UserJpaEntity entity = toEntity(user);
        UserJpaEntity saved = userJpaRepository.save(entity);
        return toDomain(saved);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userJpaRepository.findById(id).map(this::toDomain);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userJpaRepository.findByEmailIgnoreCase(email).map(this::toDomain);
    }

    @Override
    public UserDetails findByLogin(String login) {
        return userJpaRepository.findByLogin(login); //.map(this::toDomain);
    }

    @Override
    public Page<User> findByNameContaining(String name, Pageable pageable) {
        return userJpaRepository.findByNameContainingIgnoreCase(name, pageable)
                .map(this::toDomain);
    }

    @Override
    public void deleteById(Long id) {
        userJpaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return userJpaRepository.existsById(id);
    }

    // mappers simples
    private User toDomain(UserJpaEntity e) {
        if (e == null) return null;
        User u = new User();

        u.setId(e.getId());
        u.setName(e.getName());
        u.setLogin(e.getLogin());
        u.setPassword(e.getPassword());
        u.setEmail(e.getEmail());
        u.setLastModifiedAt(e.getLastModifiedAt());
        if (e.getAddress() != null) {
            Address a = new Address(e.getAddress().getId(),
                    e.getAddress().getStreet(),
                    e.getAddress().getNumber(),
                    e.getAddress().getCity(),
                    e.getAddress().getCep());
            u.setAddress(a);
        }
        return u;
    }

    private UserJpaEntity toEntity(User u) {
        if (u == null) return null;
        UserJpaEntity e = new UserJpaEntity();
        e.setId(u.getId());
        e.setName(u.getName());
        e.setLogin(u.getLogin());
        e.setPassword(u.getPassword());
        e.setEmail(u.getEmail());
        e.setLastModifiedAt(u.getLastModifiedAt());
        if (u.getAddress() != null) {
            AddressJpaEntity a = new AddressJpaEntity();
            a.setId(u.getAddress().getId());
            a.setStreet(u.getAddress().getStreet());
            a.setNumber(u.getAddress().getNumber());
            a.setCity(u.getAddress().getCity());
            a.setCep(u.getAddress().getCep());
            e.setAddress(a);
        }
        return e;
    }
}
