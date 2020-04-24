package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.service.SignUpRequest;
import com.jakubowskiartur.authservice.service.AuthService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthController {

    AuthService service;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody SignUpRequest request) {
        return service.register(request);
    }
}