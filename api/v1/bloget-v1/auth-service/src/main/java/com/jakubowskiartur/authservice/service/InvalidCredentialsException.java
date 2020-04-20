package com.jakubowskiartur.authservice.service;

import org.springframework.security.authentication.AuthenticationServiceException;

public class InvalidCredentialsException extends AuthenticationServiceException {

    public InvalidCredentialsException(final String message) {
        super(message);
    }
}
