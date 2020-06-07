//package com.jakubowskiartur.authservice.config;
//
//import lombok.AccessLevel;
//import lombok.AllArgsConstructor;
//import lombok.SneakyThrows;
//import lombok.experimental.FieldDefaults;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
//import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
//import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
//import org.springframework.security.oauth2.provider.token.TokenStore;
//import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
//import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableAuthorizationServer
//@EnableResourceServer
//@AllArgsConstructor
//@FieldDefaults(level = AccessLevel.PRIVATE)
//public class AuthorizationServerConfiguration implements AuthorizationServerConfigurer {
//
//    PasswordEncoder passwordEncoder;
//    DataSource dataSource;
//    AuthenticationManager authenticationManager;
//
//    @Bean
//    TokenStore tokenStore() {
//
//        return new InMemoryTokenStore();
//
//        //return new JdbcTokenStore(dataSource);
//    }
//
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) {
//
//        security
//                .checkTokenAccess("isAuthenticated()")
//                .tokenKeyAccess("permitAll()");
//    }
//
//    @Override
//    @SneakyThrows
//    public void configure(ClientDetailsServiceConfigurer clients) {
//
//        clients.inMemory()
//                .withClient("client")
//                .secret("secret")
//                .authorizedGrantTypes("ROLE_ADMIN")
//                .accessTokenValiditySeconds(120)
//                .refreshTokenValiditySeconds(600);
////                .jdbc(dataSource)
////                .passwordEncoder(passwordEncoder);
//    }
//
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) {
//
//        endpoints
//                .prefix("/")
//                .tokenStore(tokenStore())
//                .authenticationManager(authenticationManager);
//    }
//}
