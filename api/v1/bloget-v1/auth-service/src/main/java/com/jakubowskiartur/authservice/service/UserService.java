package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;

import java.util.List;

public interface UserService {

    List<User> receiveUsers();
    User receiveUserById(Long id);
    User receiveByUsername(String username);
    void changePassword(String newPassword);
    void changeRoles(Long id, List<Role> roles);
    void deletePrincipalAccount();
    void deleteUserById(Long id);
}
