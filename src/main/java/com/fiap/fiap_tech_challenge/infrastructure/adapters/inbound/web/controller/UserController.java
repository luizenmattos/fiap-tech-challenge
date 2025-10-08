package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.controller;


import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.AddressDTO;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserCreateDTO;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserResponseDTO;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserUpdateDTO;
import com.fiap.fiap_tech_challenge.application.service.UserUseCase;
import com.fiap.fiap_tech_challenge.application.domain.Address;
import com.fiap.fiap_tech_challenge.application.domain.User;
import com.fiap.fiap_tech_challenge.books.model.LoginDTO;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserCreateDTO;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    private final UserUseCase userUseCase;
    private final AuthenticationManager authenticationManager;

    public UserController(UserUseCase userUseCase, AuthenticationManager authenticationManager) {
        this.userUseCase = userUseCase;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDTO loginRequest) {
        Authentication authenticationRequest =
                UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.getLogin(), loginRequest.getPassword());
        Authentication authenticationResponse =
                this.authenticationManager.authenticate(authenticationRequest);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity<UserResponseDTO> create(@Valid @RequestBody UserCreateDTO dto){
        User domain = toDomain(dto);
        User created = userUseCase.create(domain);
        UserResponseDTO response = toResponse(created);
        return ResponseEntity.created(URI.create("/api/v1/users/" + created.getId())).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getById(@PathVariable Long id) {
        User u = userUseCase.findById(id);
        return ResponseEntity.ok(toResponse(u));
    }

    @GetMapping
    public ResponseEntity<Page<UserResponseDTO>> searchByName(
            @RequestParam(required = false, defaultValue = "") String name,
            @PageableDefault(size = 10) Pageable pageable) {
        Page<User> page = userUseCase.searchByName(name, pageable);
        Page<UserResponseDTO> dtoPage = page.map(this::toResponse);
        return ResponseEntity.ok(dtoPage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> update(@PathVariable Long id, @Valid @RequestBody UserUpdateDTO dto) {
        User domain = toDomain(dto);
        User updated = userUseCase.update(id, domain);
        return ResponseEntity.ok(toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    private User toDomain(UserCreateDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setLogin(dto.getLogin());
        u.setPassword(dto.getPassword());
        u.setEmail(dto.getEmail());
        if (dto.getAddress() != null) {
            Address a = new Address();
            a.setStreet(dto.getAddress().getStreet());
            a.setNumber(dto.getAddress().getNumber());
            a.setCity(dto.getAddress().getCity());
            a.setCep(dto.getAddress().getCep());
            u.setAddress(a);
        }
        return u;
    }

    private User toDomain(UserUpdateDTO dto) {
        User u = new User();
        u.setName(dto.getName());
        u.setLogin(dto.getLogin());
        u.setEmail(dto.getEmail());
        if (dto.getAddress() != null) {
            Address a = new Address();
            a.setStreet(dto.getAddress().getStreet());
            a.setNumber(dto.getAddress().getNumber());
            a.setCity(dto.getAddress().getCity());
            a.setCep(dto.getAddress().getCep());
            u.setAddress(a);
        }
        return u;
    }

    private UserResponseDTO toResponse(User u) {
        UserResponseDTO r = new UserResponseDTO();
        r.setId(u.getId());
        r.setName(u.getName());
        r.setLogin(u.getLogin());
        r.setEmail(u.getEmail());
        r.setLastModifiedAt(u.getLastModifiedAt());
        if (u.getAddress() != null) {
            AddressDTO ad = new AddressDTO();
            ad.setStreet(u.getAddress().getStreet());
            ad.setNumber(u.getAddress().getNumber());
            ad.setCity(u.getAddress().getCity());
            ad.setCep(u.getAddress().getCep());
            r.setAddress(ad);
        }
        return r;
    }
}
