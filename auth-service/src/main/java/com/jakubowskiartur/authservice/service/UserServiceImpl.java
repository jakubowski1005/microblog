package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.RoleRepository;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    PasswordValidator validator;

    @Override
    public List<User> receiveUsers() {
        return userRepository.findAll();
    }

    @Override
    public User receiveUserById(Long id) {
        return userRepository
                .findById(id)
                .orElse(null);
    }

    @Override
    public User receiveByUsername(String username) {
        return userRepository
                .findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User with this username doesn't exist."));
    }

    @Override
    public void changePassword(String newPassword) {
        if (!validator.validate(newPassword)) {
            throw new InvalidCredentialsException(
                    "Password must contain minimum eight characters, at least one uppercase, one lowercase and one number."
            );
        }

        userRepository
                .findByUsername(getLoggedInUser())
                .map(user -> {
                    user.setPassword(encoder.encode(newPassword));
                    log.info("User: {} change his password. [{}]", getLoggedInUser(), new Date());
                    return userRepository.save(user);
                });
    }

    @Override
    public void addRole(Long id, String role) {
        Role found = roleRepository.findByName(role).orElseThrow(
                () -> new IllegalArgumentException(role + " does not exist!")
        );

        userRepository.findById(id).map(user -> {
            List<Role> roles = user.getRoles();
            if (!roles.contains(found)) {
                roles.add(found);
                user.setRoles(roles);
            }

            log.info("User with ID {} now has roles: {}. [{}]", getLoggedInUser(), roles, new Date());
            return userRepository.save(user);
        });
    }

    @Override
    public void deletePrincipalAccount() {
        User user = userRepository
                .findByUsername(getLoggedInUser())
                .orElseThrow(() -> new UsernameNotFoundException("Principal doesn't exist."));

        log.info("User: {} delete account. [{}]", getLoggedInUser(), new Date());
        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        log.info("Admin: {} delete account with ID {}. [{}]", getLoggedInUser(), id, new Date());
        userRepository.deleteById(id);
    }

    private String getLoggedInUser() {
        User loggedInUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return loggedInUser == null ? null : loggedInUser.getUsername();
    }
}