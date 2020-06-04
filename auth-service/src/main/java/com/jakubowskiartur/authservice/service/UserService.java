package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;

import java.util.List;

@Deprecated
public interface UserService {

    List<User> receiveUsers();
    User receiveUserById(Long id);
    User receiveByUsername(String username);
    void changePassword(String newPassword);
    void addRole(Long id, String role);
    void deletePrincipalAccount();
    void deleteUserById(Long id);
}
