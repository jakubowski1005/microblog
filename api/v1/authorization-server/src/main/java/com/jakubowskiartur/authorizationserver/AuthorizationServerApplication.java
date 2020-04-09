package com.jakubowskiartur.authorizationserver;

import com.jakubowskiartur.authorizationserver.model.User;
import com.jakubowskiartur.authorizationserver.repository.UserDetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableAuthorizationServer
@RestController
public class AuthorizationServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthorizationServerApplication.class, args);
    }

    @Autowired UserDetailsRepository repository;

    @GetMapping("/test")
    public List<User> receiveUsers() {
        return repository.findAll();
    }
}
