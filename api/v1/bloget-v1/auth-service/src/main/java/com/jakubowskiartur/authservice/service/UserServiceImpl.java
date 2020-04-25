package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.RoleRepository;
import com.jakubowskiartur.authservice.repository.UserRepository;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;

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
        validatePassword(newPassword);
        userRepository
                .findByUsername(getLoggedInUser())
                .map(user -> {
                    user.setPassword(encoder.encode(newPassword));
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
            return userRepository.save(user);
        });
    }

    @Override
    public void deletePrincipalAccount() {
        User user = userRepository
                .findByUsername(getLoggedInUser())
                .orElseThrow(() -> new UsernameNotFoundException("Principal doesn't exist."));

        userRepository.delete(user);
    }

    @Override
    public void deleteUserById(Long id) {
        userRepository.deleteById(id);
    }

    private void validatePassword(String password) {
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
            throw new InvalidCredentialsException(
                    "Password must contain minimum eight characters, at least one uppercase, one lowercase and one number."
            );
        }
    }

    private String getLoggedInUser() {
        User loggedInUser = (User) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return loggedInUser == null ? null : loggedInUser.getUsername();
    }

    //TODO Add logging
}