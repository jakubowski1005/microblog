package com.jakubowskiartur.authorizationserver.controller;

import com.jakubowskiartur.authorizationserver.model.User;
import com.jakubowskiartur.authorizationserver.payloads.SignInRequest;
import com.jakubowskiartur.authorizationserver.payloads.SignUpRequest;
import com.jakubowskiartur.authorizationserver.repository.RoleRepository;
import com.jakubowskiartur.authorizationserver.repository.UserDetailsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class AuthController {

    UserDetailsRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    RestTemplate restTemplate;

    //register user
    @PostMapping("/register")
    public User register(@RequestBody SignUpRequest request) {
        User user = new User(null, request.getLogin(), encoder.encode(request.getPassword()), request.getEmail(), true, true, true, true, null);
        user.setRoles(Collections.singletonList(roleRepository.findByName("ROLE_USER").orElse(null)));

        return userRepository.save(user);
    }

    //login user
    @PostMapping("/login")
    public Object login(@RequestBody SignInRequest request) {

        int port = 8081;
        String host = "localhost";
        String login = request.getLogin();
        String pass = request.getPassword();

        String path = String.format("http://%s:%s@%s:%d/oauth/token", login, pass, host, port);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        //httpHeaders.add("Content-Type", "application/x-www-form-urlencoded");
        log.debug(path);
        return restTemplate.postForObject(path, null, Object.class, headers);
        //return restTemplate.exchange(path, HttpMethod.POST, null, Object.class, headers);

    }
}
