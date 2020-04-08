//package com.jakubowskiartur.authorizationserver.config;
//
//import lombok.SneakyThrows;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//
//@Configuration
//public class AuthServerConfig extends WebSecurityConfigurerAdapter implements AuthorizationServerConfigurer {
//
//    @Bean
//    @SneakyThrows
//    public AuthenticationManager authenticationManager() {
//        return super.authenticationManager();
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//
//        security.checkTokenAccess("permitAll()");
//    }
//
//    @Override
//    @SneakyThrows
//    public void configure(ClientDetailsServiceConfigurer clients) {
//
//        clients
//                .inMemory()
//                .withClient("web")
//                .secret(passwordEncoder().encode("webpass"))
//                .scopes("READ", "WRITE")
//                .authorizedGrantTypes("password", "authorization_code");
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//
//        endpoints.authenticationManager(authenticationManager());
//    }
//}
