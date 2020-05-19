package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.service.UserServiceImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserController {

    UserServiceImpl service;

    @GetMapping("/users")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public List<User> receiveUsers() {
        return service.receiveUsers();
    }

    @GetMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User receiveUserById(@PathVariable Long id) {
        return service.receiveUserById(id);
    }

    @GetMapping("/users/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public User receiveByUsername(@PathVariable String username) {
        return service.receiveByUsername(username);
    }

    @PutMapping("/users/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void changePassword(@RequestBody @Valid String password) {
        service.changePassword(password);
    }

    @PutMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void addRole(@PathVariable Long id, @RequestBody String role) {
        service.addRole(id, role);
    }

    @DeleteMapping("/users/me")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deletePrincipalAccount() {
        service.deletePrincipalAccount();
    }

    @DeleteMapping("/users/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
    }
}