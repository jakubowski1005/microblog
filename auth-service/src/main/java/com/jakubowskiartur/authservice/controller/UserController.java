package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.AuthRequest;
import com.jakubowskiartur.authservice.payload.RegisterRequest;
import com.jakubowskiartur.authservice.repository.UserRepository;
import com.jakubowskiartur.authservice.service.AuthHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class UserController {

    private AuthHandler handler;

    public UserController(AuthHandler handler) {
        this.handler = handler;
    }

    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody AuthRequest request) {
        return handler.login(request);
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<User>> register(@RequestBody RegisterRequest request) {
        return handler.register(request);
    }
}
