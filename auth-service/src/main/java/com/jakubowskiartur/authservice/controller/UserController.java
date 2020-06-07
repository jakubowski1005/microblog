package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.model.MongoUser;
import com.jakubowskiartur.authservice.service.UserHandler;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@Validated
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserHandler handler;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Flux<MongoUser> receiveUsers() {
        return handler.receiveUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<MongoUser> receiveUserById(@PathVariable String id) {
        return handler.receiveUserById(id);
    }

    @GetMapping("/users/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<MongoUser> receiveByUsername(@PathVariable String username) {
        return handler.receiveByUsername(username);
    }

    @PutMapping("/users/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<MongoUser>> changePassword(@RequestBody String password) {
        return handler.changePassword(password);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<MongoUser>> addRole(@PathVariable String id, @RequestBody String role) {
        return handler.addRole(id, role);
    }

    @DeleteMapping("/users/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<ResponseEntity<Void>> deletePrincipalAccount() {
        return handler.deletePrincipalAccount();
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String id) {
        return handler.deleteUserById(id);
    }
}
