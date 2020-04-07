package com.jakubowskiartur.authorizationserver.config;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.experimental.FieldDefaults;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserConfig extends GlobalAuthenticationConfigurerAdapter {

    PasswordEncoder passwordEncoder;

    @Override
    @SneakyThrows
    public void init(AuthenticationManagerBuilder auth) {

        auth
                .inMemoryAuthentication()
                .withUser("artur")
                .password(passwordEncoder.encode("pass123"))
                .roles("USER", "ADMIN", "MANAGER").authorities("CAN_READ", "CAN_WRITE", "CAN_DELETE")
                .and()
                .withUser("martyna")
                .password(passwordEncoder.encode("pass123"))
                .roles("USER")
                .authorities("CAN_READ", "CAN_WRITE");
    }
}
