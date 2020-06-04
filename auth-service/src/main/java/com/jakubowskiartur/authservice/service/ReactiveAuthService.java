package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.MongoUser;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Mono;

public interface ReactiveAuthService {

    Mono<ResponseEntity<MongoUser>> register(SignUpRequest request);
}
