package com.jakubowskiartur.authorizationserver.controller;

import com.jakubowskiartur.authorizationserver.model.Role;
import com.jakubowskiartur.authorizationserver.model.User;
import com.jakubowskiartur.authorizationserver.payloads.SignInRequest;
import com.jakubowskiartur.authorizationserver.payloads.SignUpRequest;
import com.jakubowskiartur.authorizationserver.repository.UserDetailsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class AuthController {

    UserDetailsRepository repository;
    PasswordEncoder encoder;
    AuthenticationManager authenticationManager;

    //register user
    @PostMapping("/register")
    public User register(@RequestBody SignUpRequest request) {
        User user = new User(null, request.getLogin(), encoder.encode(request.getPassword()), request.getEmail(), true, true, true, true, null);
        //user.setRoles(repository.findByUsername("martynka_user").get().getRoles());

        return repository.save(user);
    }

//    //login user
//    @PostMapping("/login")
//    public Object login(@RequestBody SignInRequest request) {
//
//        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
//                request.getLogin(),
//                request.getPassword()
//        ));
//
//        SecurityContext ctx = SecurityContextHolder.getContext();
//
//        ctx.setAuthentication(authentication);
//
//        return ctx.getAuthentication().getPrincipal();
    }
}
