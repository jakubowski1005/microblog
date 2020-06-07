package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Collections;

import static org.mockito.Mockito.*;

public class AuthHandlerTest {

    AuthHandler handler;
    MongoUserRepository repository;
    PasswordEncoder encoder;

    @BeforeEach
    public void setup() {
        repository = mock(MongoUserRepository.class);
        encoder = mock(PasswordEncoder.class);
        handler = new AuthHandler(repository, encoder);
    }

    @Test
    public void shouldRegister_whenCorrectCredentials() {
        //given
        var request = new SignUpRequest("username", "user@gmail.com", "Pass1234");
        var user = MongoUser.builder()
                .username(request.getLogin())
                .email(request.getEmail())
                .password(request.getPassword())
                .roles(Collections.singletonList("ROLE_USER"))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        when(repository.findByUsernameAndEmail(anyString(), anyString()))
                .thenReturn(Mono.empty());
//        when(repository.existsByUsername(anyString()))
//                .thenReturn(false);
        when(repository.save(any(MongoUser.class)))
                .thenReturn(Mono.just(user));
        when(encoder.encode(anyString()))
                .thenReturn("Pass1234");

        //when
        var response = handler.register(request);

        //then
        StepVerifier.create(response.log())
                .expectNext(ResponseEntity.status(201).body(user))
                .verifyComplete();
    }

    @Test
    public void  shouldReturnErr_whenBadCredentials() {
        //given
        var request = new SignUpRequest("username", "user@gmail.com", "Pass1234");
        when(repository.findByUsernameAndEmail(anyString(), anyString()))
                .thenReturn(Mono.just(MongoUser.builder().username("username").build()));
//        when(repository.existsByEmail(anyString()))
//                .thenReturn(true);
//        when(repository.existsByUsername(anyString()))
//                .thenReturn(true);

        //when
        var response = handler.register(request);

        //then
        StepVerifier.create(response.log())
                .expectNext(ResponseEntity.badRequest().build())
                .verifyComplete();

    }
}
