package com.jakubowskiartur.authservice.service;

import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public interface AuthHandler {
    Mono<ServerResponse> register(ServerRequest request);
    Mono<ServerResponse> login(ServerRequest request);
}
