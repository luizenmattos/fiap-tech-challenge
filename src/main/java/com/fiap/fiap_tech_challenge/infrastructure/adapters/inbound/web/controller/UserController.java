package com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.controller;

import com.fiap.fiap_tech_challenge.infrastructure.adapters.inbound.web.dto.*;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCreateOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserCrudPort;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserReadOutput;
import com.fiap.fiap_tech_challenge.application.port.inbound.UserUpdateOutput;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.MediaType;
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

    @PostMapping(path = "/login",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> login(
            @RequestBody LoginRequest loginRequest) {

         return ResponseEntity.ok().body(
            userCrudPort.login(loginRequest.getLogin(), loginRequest.getPassword())
         );
    }


    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> create(
            @RequestHeader(value = "Authorization", required = true) String token,
            @Valid @RequestBody UserCreateRequest dto){
        UserCreateOutput user = userCrudPort.create(token,dto.toInput());
        return ResponseEntity.ok().body(UserResponse.fromOutput(user));
    }

    @GetMapping(path = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> getById(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id
    ) {
        UserReadOutput user = userCrudPort.findById(token,id);
        return ResponseEntity.ok().body(UserResponse.fromOutput(user));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
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

    @GetMapping(path="/findByName/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserResponse>> findByName(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable String name
    ) {
        System.out.println(name);
        List<UserResponse> usersResponse = new ArrayList<>();

        List<UserReadOutput> usersOutput = userCrudPort.findByName(token,name);
        for(UserReadOutput user : usersOutput){
            usersResponse.add(UserResponse.fromOutput(user));

        }
        return ResponseEntity.ok().body(usersResponse);
    }

    @PutMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> update(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id,
            @Valid @RequestBody UserUpdateRequest dto
    ) {
        UserUpdateOutput userOutput = userCrudPort.udpate(token,id, dto.toInput());
        return ResponseEntity.ok(UserResponse.fromOutput(userOutput));
    }

    @PutMapping(path="/new-password",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserResponse> changePassword(
            @RequestHeader(value = "Authorization", required = true) String token,
            @Valid @RequestBody UserUpdatePassword dto
    ) {
        userCrudPort.changePassword(token, dto.toInput());
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping(path="/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> delete(
            @RequestHeader(value = "Authorization", required = true) String token,
            @PathVariable Long id
    ) {
        userCrudPort.deleteById(token,id);
        return ResponseEntity.noContent().build();
    }

}
