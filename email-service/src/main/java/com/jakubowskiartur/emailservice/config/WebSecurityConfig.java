package com.jakubowskiartur.emailservice.config;

import lombok.SneakyThrows;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @SneakyThrows
    protected void configure(HttpSecurity http) {
        http.authorizeRequests()
                .anyRequest().permitAll()
                .and()
                .httpBasic().disable();
    }
}
