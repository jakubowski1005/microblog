package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthHandler implements AuthService {

    MongoUserRepository repository;
    PasswordEncoder encoder;

    @Override
    public Mono<ResponseEntity<MongoUser>> register(SignUpRequest request) {
        return repository.save(createFromRequest(request))
                .map(created -> ResponseEntity.status(201).body(created));
    }

    private MongoUser createFromRequest(SignUpRequest request) {
        validateCredentials(request.getLogin(), request.getEmail());

        return  MongoUser.builder()
                .username(request.getLogin())
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .roles(Collections.singletonList("ROLE_USER"))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();
    }

    private void validateCredentials(String login, String email) {
        if (repository.existsByUsername(login)) {
            String message = String.format("User with a username \"%s\" already exists.", login);
            log.debug(message);
            throw new InvalidCredentialsException(message);
        }

        if (repository.existsByEmail(email)) {
            String message = String.format("User with an e-mail \"%s\" already exists.", email);
            log.debug(message);
            throw new InvalidCredentialsException(message);
        }
    }
}
