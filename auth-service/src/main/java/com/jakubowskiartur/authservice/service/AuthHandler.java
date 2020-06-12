package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.AuthRequest;
import com.jakubowskiartur.authservice.payload.RegisterRequest;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface AuthHandler {
    Mono<ResponseEntity<User>> register(RegisterRequest request);
    Mono<ResponseEntity<?>> login(AuthRequest request);
}
