package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TestUserHandler {

    public Flux<User> findAll() {
        var user1 = new User("artur");
        var user2 = new User("martynka");
        var user3 = new User("dickhead");
        return Flux.just(user1, user2, user3);
    }

    public Mono<User> findByUsername(String username) {
        var user1 = new User("artur");
        var user2 = new User("martynka");

        if (username.equals("artur")) return Mono.just(user1);
        if (username.equals("martynka")) return Mono.just(user2);
        return Mono.empty();
    }
}
