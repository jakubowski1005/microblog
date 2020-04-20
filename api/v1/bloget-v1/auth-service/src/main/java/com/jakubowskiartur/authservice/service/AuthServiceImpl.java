package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.payload.SignUpRequest;
import com.jakubowskiartur.authservice.repository.RoleRepository;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class AuthServiceImpl implements AuthService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

    @Override
    public ResponseEntity<?> register(SignUpRequest request) {

        String login = request.getLogin();
        String email = request.getEmail();
        String password = request.getPassword();

        validateCredentials(login, email);

        User user = User.builder()
                .username(login)
                .email(email)
                .password(encoder.encode(password))
                .roles(Collections.singletonList(roleRepository.getOne(1L)))
                .enabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialsNonExpired(true)
                .build();

        userRepository.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    private void validateCredentials(String login, String email) {

        if (userRepository.existsByUsername(login)) {
            String message = String.format("User with a username \"%s\" already exists.", login);
            log.debug(message);
            throw new InvalidCredentialsException(message);
        }

        if (userRepository.existsByEmail(email)) {
            String message = String.format("User with an e-mail \"%s\" already exists.", email);
            log.debug(message);
            throw new InvalidCredentialsException(message);
        }
    }
}
