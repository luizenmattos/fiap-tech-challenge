package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.controller;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.LoginRequest;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserCreateRequest;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserResponse;
import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.UserUpdateRequest;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCrudPort;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;

// import org.springframework.security.authentication.AuthenticationManager;
// import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
// import org.springframework.security.core.Authentication;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController  {

    private final UserCrudPort userCrudPort;
    // private final AuthenticationManager authenticationManager;

    public UserController(UserCrudPort userService) {
        // this.authenticationManager = authenticationManager;
        this.userCrudPort = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {

         return ResponseEntity.ok().body(
            userCrudPort.login(loginRequest.getLogin(), loginRequest.getPassword())
         );
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(
            @RequestHeader(value = "Authorization", required = true) String token,
            @Valid @RequestBody UserCreateRequest dto){
        UserCreateOutput user = userCrudPort.create(dto.toInput());
        return ResponseEntity.ok().body(UserResponse.fromOutput(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getById(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id
    ) {
        UserReadOutput user = userCrudPort.findById(id);
        return ResponseEntity.ok().body(UserResponse.fromOutput(user));
    }

    // @GetMapping
    // public ResponseEntity<Page<UserResponseDTO>> searchByName(
    //         @RequestParam(required = false, defaultValue = "") String name,
    //         @PageableDefault(size = 10) Pageable pageable) {
    //     Page<User> page = userUseCase.searchByName(name, pageable);
    //     Page<UserResponseDTO> dtoPage = page.map(this::toResponse);
    //     return ResponseEntity.ok(dtoPage);
    // }

    @GetMapping
    public ResponseEntity<List<UserResponse>> findAll(
            @RequestHeader(value = "Authorization", required = true) String token
    ) {
        List<UserResponse> usersResponse = new ArrayList<>();

        List<UserReadOutput> usersOutput = userCrudPort.findAll(token);
        for(UserReadOutput user : usersOutput){
            usersResponse.add(UserResponse.fromOutput(user));

        }
        return ResponseEntity.ok().body(usersResponse);
    }

    @GetMapping("/findByName/{name}")
    public ResponseEntity<List<UserResponse>> findByName(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable String name
    ) {
        System.out.println(name);
        List<UserResponse> usersResponse = new ArrayList<>();

        List<UserReadOutput> usersOutput = userCrudPort.findByName(name);
        for(UserReadOutput user : usersOutput){
            usersResponse.add(UserResponse.fromOutput(user));

        }
        return ResponseEntity.ok().body(usersResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserResponse> update(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest dto
    ) {
        UserUpdateOutput userOutput = userCrudPort.udpate(id, dto.toInput());
        return ResponseEntity.ok(UserResponse.fromOutput(userOutput));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id
    ) {
        userCrudPort.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
