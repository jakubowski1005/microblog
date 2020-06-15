package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.security.Principal;

public interface UserHandler {
    Mono<User> receiveUserById(String id);
    Mono<User> receiveUserByUsername(String username);
    Mono<String> receivePrincipal(Mono<Principal> principal);
    Mono<ResponseEntity<?>> updatePassword(String newPassword);
    Mono<ResponseEntity<User>> addRole(String id, Role role);
    Mono<ResponseEntity<Void>> deleteUserById(String id);
}
