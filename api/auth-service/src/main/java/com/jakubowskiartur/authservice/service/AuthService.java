package com.jakubowskiartur.authservice.service;

import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> register(SignUpRequest request);
}
