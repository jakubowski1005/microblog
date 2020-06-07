//package com.jakubowskiartur.authservice.service;
//
//import com.jakubowskiartur.authservice.model.User;
//import com.jakubowskiartur.authservice.model.UserDetailsImpl;
//import com.jakubowskiartur.authservice.repository.UserRepository;
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import org.springframework.security.authentication.AccountStatusUserDetailsChecker;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Deprecated
//@Service("userDetailsService")
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    UserRepository repository;
//
//    @Override
//    @SneakyThrows
//    public UserDetails loadUserByUsername(String username) {
//        User optionalUser = repository.findByUsername(username).orElseThrow(
//                () -> new UsernameNotFoundException("Credentials are not valid.")
//        );
//
//        UserDetails userDetails = new UserDetailsImpl(optionalUser);
//
//        new AccountStatusUserDetailsChecker().check(userDetails);
//        return userDetails;
//    }
//}
