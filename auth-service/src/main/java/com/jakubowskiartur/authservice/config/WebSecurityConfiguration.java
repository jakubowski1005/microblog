//package com.jakubowskiartur.authservice.config;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.builders.WebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.factory.PasswordEncoderFactories;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//@Configuration
//@EnableGlobalMethodSecurity(prePostEnabled = true)
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//@EnableWebFluxSecurity
//public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {
//
//    UserDetailsService userDetailsService;
//
//    @Bean
//    @SneakyThrows
//    public AuthenticationManager authenticationManagerBean() {
//        return super.authenticationManagerBean();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
//
//    @Override
//    @SneakyThrows
//    protected void configure(AuthenticationManagerBuilder auth) {
//
//        auth
//                .userDetailsService(userDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//
//    @Override
//    @SneakyThrows
//    protected void configure(HttpSecurity http) {
//
//        http
//                .cors()
//                .and()
//                .csrf()
//                .disable()
//                .authorizeRequests()
//                .antMatchers("/login", "/register")
//                .permitAll()
//                .anyRequest()
//                .authenticated();
//    }
//
//    @Override
//    public void configure(WebSecurity web) {
//
//        web
//                .ignoring()
//                .antMatchers("/login", "/register");
//    }
//}
