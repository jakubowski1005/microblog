package com.jakubowskiartur.authservice.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class ContentAPI {

        @GetMapping("/messages")
        @PreAuthorize("hasRole('USER')")
        public Mono<ResponseEntity<?>> user() {
            return Mono.just(ResponseEntity.ok("Message"));
        }
}
