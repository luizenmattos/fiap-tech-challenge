// package com.fiap.fiap_tech_challenge.infrastructure.adapters.config;

// import com.fiap.fiap_tech_challenge.infrastructure.adapters.outbound.persistence.repository.UserJpaRepository;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class AuthorizationService implements UserDetailsService {

//     @Autowired
//     private UserJpaRepository repository;

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         return repository.findByLogin(username).get();
//     }
// }
