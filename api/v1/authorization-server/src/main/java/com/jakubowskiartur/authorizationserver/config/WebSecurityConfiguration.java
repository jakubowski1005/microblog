package com.jakubowskiartur.authorizationserver.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    UserDetailsService userDetailsService;

    @Bean
    @SneakyThrows
    public AuthenticationManager authenticationManagerBean() {
        return super.authenticationManagerBean();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    @SneakyThrows
    protected void configure(AuthenticationManagerBuilder auth) {

        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}