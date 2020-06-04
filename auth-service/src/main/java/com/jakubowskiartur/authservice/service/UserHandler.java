package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserHandler implements ReactiveUserService {

    MongoUserRepository repository;
    PasswordEncoder encoder;

    @Override
    public Flux<MongoUser> receiveUsers() {
        return repository.findAll();
    }

    @Override
    public Mono<MongoUser> receiveUserById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<MongoUser> receiveByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Mono<ResponseEntity<MongoUser>> changePassword(String newPassword) {
        return repository.findByUsername(getLoggedInUser())
                .flatMap(user -> {
                    user.setPassword(encoder.encode(newPassword));
                    return repository.save(user);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<MongoUser>> addRole(String id, String role) {
        return repository.findByUsername(getLoggedInUser())
                .flatMap(user -> {
                    var roles = user.getRoles();
                    if (!roles.contains(role)) {
                        roles.add(role);
                        user.setRoles(roles);
                    }
                    return repository.save(user);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deletePrincipalAccount() {
        return repository.findByUsername(getLoggedInUser())
                .flatMap(user -> repository.delete(user)
                .then(Mono.just(ResponseEntity.noContent().build())));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUserById(String id) {
        return repository.findById(id)
                .flatMap(student -> repository.delete(student)
                        .then(Mono.just(ResponseEntity.noContent().<Void>build()))
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }

    private String getLoggedInUser() {
        MongoUser loggedInUser = (MongoUser) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return loggedInUser == null ? null : loggedInUser.getUsername();
    }
}
