package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.payload.SignUpRequest;
import org.springframework.http.ResponseEntity;

public interface AuthService {

    ResponseEntity<?> register(SignUpRequest request);
}
