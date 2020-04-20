package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserService {

    UserRepository repository;
    PasswordEncoder encoder;

    public List<User> receiveUsers() {
        return repository.findAll();
    }

    public User receiveUserById(Long id) {
        return repository
                .findById(id)
                .orElse(null);
    }

    public void changePassword(String newPassword) {

        validatePassword(newPassword);
        repository
                .findByUsername(getLoggedInUser())
                .map(user -> {
                    user.setPassword(encoder.encode(newPassword));
                    return repository.save(user);
                });
    }

    public void changeRoles(List<Role> roles) {

        repository
                .findByUsername(getLoggedInUser())
                .map(user -> {
                    roles.addAll(user.getRoles());
                    user.setRoles(roles.stream()
                            .distinct()
                            .collect(Collectors.toList()));
                    return repository.save(user);
                });
    }

    private void validatePassword(String password) {

        if (password.length() < 8) {
            throw new InvalidCredentialsException("Password must be at least 8 characters.");
        }

        if (!password.matches("^(?=.*[0-9])")) {
            throw new InvalidCredentialsException("Password must contain at least 1 number.");
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])")) {
            throw new InvalidCredentialsException("Password must contain capital and small case letters.");
        }
    }

    private String getLoggedInUser() {

        User loggedInUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return loggedInUser.getUsername();
    }



    //    GET		users		ROLE ADMIN
//    GET		user by id		ONLY PRINCIPAL
//    PUT		changePass	ONLY PRINCIPAL
//    PUT		set role		ROLE ADMIN
//    DELETE	user			ONLY PRINCIPAL
//    DELETE	user by id		ROLE ADMIN
}
