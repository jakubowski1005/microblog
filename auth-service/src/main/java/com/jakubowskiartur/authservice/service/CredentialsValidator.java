package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class CredentialsValidator {

    private PasswordValidator passwordValidator;
    private MongoUserRepository repository;

    public CredentialsValidator(PasswordValidator passwordValidator, MongoUserRepository repository) {
        this.passwordValidator = passwordValidator;
        this.repository = repository;
    }

    public boolean isValid(SignUpRequest request) {
        String login = request.getLogin();
        String email = request.getEmail();
        String password = request.getPassword();

        return (repository.existsByUsername(login) ||
                repository.existsByEmail(email) ||
                passwordValidator.validate(password));

    }
}
