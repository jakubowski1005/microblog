package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.security.Principal;

@Service
@AllArgsConstructor
@Slf4j
public class UserHandlerImpl implements UserHandler {

    private final static String PASSWORD_PATTERN = "((?=.*[a-z])(?=.*\\d)(?=.*[A-Z]).{8,40})";

    private UserRepository repository;
    private PasswordEncoder encoder;

    @Override
    public Mono<User> receiveUserById(String id) {
        return repository.findById(id);
    }

    @Override
    public Mono<User> receiveUserByUsername(String username) {
        return repository.findByUsername(username);
    }

    @Override
    public Mono<String> receivePrincipal(Mono<Principal> principal) {
        return principal.map(Principal::getName);
    }

    @Override
    public Mono<ResponseEntity<?>> updatePassword(String newPassword, Mono<Principal> principal) {
        // TODO Error here
        return receivePrincipal(principal)
                .flatMap(repository::findByUsername)
                .map(user -> {
                    if (!newPassword.matches(PASSWORD_PATTERN)) {
                        return ResponseEntity.badRequest().build();
                    }
                    user.setPassword(encoder.encode(newPassword));
                    var updated = repository.save(user);
                    return ResponseEntity.ok(updated);
                })
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<User>> addRole(String id, Role role) {
        return repository.findById(id)
                .flatMap(user -> {
                    var roles = user.getRoles();
                        roles.add(role);
                        user.setRoles(roles);
                    return repository.save(user);
                })
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteUserById(String id) {
        return repository.findById(id)
                .flatMap(user -> repository.delete(user)
                        .then(Mono.just(ResponseEntity.ok().<Void>build()))
                ).defaultIfEmpty(ResponseEntity.notFound().build());
    }
}
