package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.Role;
import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.RoleRepository;
import com.jakubowskiartur.authservice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServiceImplTest {

    AuthService service;
    UserRepository userRepository;
    RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        service = new AuthServiceImpl(userRepository, roleRepository, mock(PasswordEncoder.class));
    }

    @Test
    public void shouldProperlyValidateCredentials() {
        //given
        String password = "Pass1234";
        String wrongUsername = "Wrong username";
        String correctUsername = "Correct username";
        String wrongEmail = "Wrong email";
        String correctEmail = "Correct email";

        //when
        when(userRepository.existsByEmail(wrongEmail)).thenReturn(true);
        when(userRepository.existsByEmail(correctEmail)).thenReturn(false);
        when(userRepository.existsByUsername(wrongUsername)).thenReturn(true);
        when(userRepository.existsByUsername(correctUsername)).thenReturn(false);

        //then
        assertThrows(
                InvalidCredentialsException.class,
                () -> service.register(new SignUpRequest(correctUsername, wrongEmail, password))
        );
        assertThrows(
                InvalidCredentialsException.class,
                () -> service.register(new SignUpRequest(wrongUsername, correctEmail, password))
        );
    }

    @Test
    public void shouldProperlyBuildUserObject() {
        //given
        String username = "mewtoo";
        String email = "mew@too.pl";
        String password = "Pass1234";
        SignUpRequest request = new SignUpRequest(username, email, password);

        //when
        when(userRepository.existsByEmail(email)).thenReturn(false);
        when(userRepository.existsByUsername(username)).thenReturn(false);
        when(roleRepository.getOne(anyLong())).thenReturn(new Role(1L, "ROLE_USER"));

        ResponseEntity<?> response = service.register(request);
        User created = (User) response.getBody();

        //then
        assertThat(response.getStatusCodeValue()).isEqualTo(201);
        assertThat(created).isNotNull();
        assertThat(created.getUsername()).isEqualTo(username);
        assertThat(created.getRoles().get(0).getName()).isEqualTo("ROLE_USER");
    }
}