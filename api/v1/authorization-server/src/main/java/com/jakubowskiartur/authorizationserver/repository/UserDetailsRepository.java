package com.jakubowskiartur.authorizationserver.repository;

import com.jakubowskiartur.authorizationserver.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);
}
