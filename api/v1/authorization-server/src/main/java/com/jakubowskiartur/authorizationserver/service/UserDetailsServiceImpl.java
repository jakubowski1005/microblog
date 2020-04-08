package com.jakubowskiartur.authorizationserver.service;

import com.jakubowskiartur.authorizationserver.model.AuthUserDetails;
import com.jakubowskiartur.authorizationserver.model.User;
import com.jakubowskiartur.authorizationserver.repository.UserDetailsRepository;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDetailsServiceImpl implements UserDetailsService {

    UserDetailsRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User optionalUser = repository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Credentials are not valid.")
        );

        UserDetails userDetails = new AuthUserDetails(optionalUser);

        new AccountStatusUserDetailsChecker().check(userDetails);
        return userDetails;
    }
}
