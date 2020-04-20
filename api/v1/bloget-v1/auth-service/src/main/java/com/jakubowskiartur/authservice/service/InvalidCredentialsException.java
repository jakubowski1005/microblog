package com.jakubowskiartur.authservice.service;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FORBIDDEN)
public class InvalidCredentialsException extends AuthenticationServiceException {

    public InvalidCredentialsException(final String message) {
        super(message);
    }
}
