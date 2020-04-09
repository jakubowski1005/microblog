package com.jakubowskiartur.authorizationserver.controller;

import com.jakubowskiartur.authorizationserver.model.User;
import com.jakubowskiartur.authorizationserver.repository.UserDetailsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserController {

    UserDetailsRepository repository;
//    GET		users		ROLE ADMIN
//    GET		user by id		ONLY PRINCIPAL
//    POST	user			EVERYONE (Register user)
//    PUT		changePass	ONLY PRINCIPAL
//    PUT		set role		ROLE ADMIN
//    DELETE	user			ONLY PRINCIPAL
//    DELETE	user by id		ROLE ADMIN

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> receiveUsers() {
        return repository.findAll();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User receiveUserById(@PathVariable Long id) {
        return repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("User with this id does not exist.")
        );
    }

    @GetMapping("/users/me")
    public User receiveOwnData() {
        User user =  (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        return repository.findByUsername(user.getUsername()).orElse(null);
    }



}
