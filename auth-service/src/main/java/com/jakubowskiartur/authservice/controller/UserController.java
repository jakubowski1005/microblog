package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.service.UserHandler;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@AllArgsConstructor
public class UserController {

    private UserHandler handler;

    @GetMapping("/users/id/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<User> receiveUserById(@PathVariable String id) {
        return handler.receiveUserById(id);
    }

    @GetMapping("/users/{username}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public Mono<User> receiveUserByUsername(@PathVariable String username) {
        return handler.receiveUserByUsername(username);
    }

    @GetMapping("/users/me")
    public Mono<String> receivePrincipal(Mono<Principal> principal) {
        return principal.map(Principal::getName);
    }

    @PutMapping("/users/me")
    public Mono<ResponseEntity<?>> updatePassword(@Valid @RequestBody String newPassword) {
        return handler.updatePassword(newPassword);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<User>> addRole(@PathVariable String id, Role role) {
        return handler.addRole(id, role);
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public Mono<ResponseEntity<Void>> deleteUserById(@PathVariable String id) {
        return handler.deleteUserById(id);
    }
}
