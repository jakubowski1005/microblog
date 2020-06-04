package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

import java.util.List;

public interface ReactiveUserService {

    Mono<List<MongoUser>> receiveUsers();
    Mono<MongoUser> receiveUserById(Long id);
    Mono<MongoUser> receiveByUsername(String username);
    Mono<ResponseEntity<Void>> changePassword(String newPassword);
    Mono<ResponseEntity<Void>> addRole(Long id, String role);
    Mono<ResponseEntity<Void>> deletePrincipalAccount();
    Mono<ResponseEntity<Void>> deleteUserById(Long id);
}
