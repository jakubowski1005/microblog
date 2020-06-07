package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class UserHandlerTest {

    UserHandler handler;
    MongoUserRepository repository;
    PasswordEncoder encoder;

    MongoUser user1;
    MongoUser user2;
    Flux<MongoUser> usersFlux;

    @BeforeEach
    public void setup() {
        repository = mock(MongoUserRepository.class);
        encoder = mock(PasswordEncoder.class);
        handler = new UserHandler(repository, encoder);

        user1 = MongoUser.builder().username("user1").build();
        user2 = MongoUser.builder().username("user1").build();
        usersFlux = Flux.just(user1, user2);
    }

    @Test
    public void shouldReceiveUsersStream() {
        //when
        when(repository.findAll())
                .thenReturn(usersFlux);

        //then
        StepVerifier.create(handler.receiveUsers().log())
                .expectNext(user1)
                .expectNext(user2)
                .verifyComplete();
    }

    @Test
    public void shouldReturnUserById() {
        //when
        when(repository.findById(anyString()))
                .thenReturn(Mono.just(user1));

        //then
        StepVerifier.create(handler.receiveUserById("123").log())
                .expectNext(user1)
                .verifyComplete();
    }

    @Test
    public void shouldReturnEmptyStream_whenUserDoesNotExist() {
        //when
        when(repository.findById(anyString()))
                .thenReturn(Mono.empty());

        //then
        StepVerifier.create(handler.receiveUserById("321"))
                .expectNextCount(0)
                .verifyComplete();
    }

    @Test
    public void shouldReceiveUserByUsername() {
        //when
        when(repository.findByUsername(anyString()))
                .thenReturn(Mono.just(user1));

        //then
        StepVerifier.create(handler.receiveByUsername("user1").log())
                .expectNext(user1)
                .verifyComplete();
    }
}
