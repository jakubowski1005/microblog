package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.ReactiveUserDetails;
import com.jakubowskiartur.authservice.repository.MongoUserRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class ReactiveUserDetailsServiceImpl implements ReactiveUserDetailsService {

    private MongoUserRepository repository;

    public ReactiveUserDetailsServiceImpl(MongoUserRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) {
        return repository.findByUsername(username)
                .map(ReactiveUserDetails::new);
    }
}
