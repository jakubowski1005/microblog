package com.jakubowskiartur.authservice.service;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.repository.RoleRepository;
import com.jakubowskiartur.authservice.repository.UserRepository;
import org.junit.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Deprecated
@Ignore
class UserServiceImplTest {

    UserService service;
    UserRepository userRepository;
    RoleRepository roleRepository;
    PasswordEncoder encoder;
    PasswordValidator validator;

    User user1;
    User user2;
    List<User> users;

    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);
        roleRepository = mock(RoleRepository.class);
        encoder = mock(PasswordEncoder.class);
        validator = mock(PasswordValidator.class);
        service = new UserServiceImpl(userRepository, roleRepository, encoder, validator);

        user1 = User.builder()
                .id(1L)
                .username("mockUser1")
                .build();

        user2 = User.builder()
                .id(2L)
                .username("mockUser2")
                .build();

      users = Arrays.asList(user1, user2);
    }

    @Test
    public void shouldReceiveUserList() {
        //when
        when(userRepository.findAll()).thenReturn(users);
        List<User> found = service.receiveUsers();

        //then
        assertThat(found).isEqualTo(users);
    }

    @Test
    public void shouldReturnUserById() {
        //when
        when(userRepository.findById(1L)).thenReturn(Optional.of(user1));

        //then
        assertThat(service.receiveUserById(1L)).isEqualTo(user1);
    }

    @Test
    public void shouldReturnNullIfUserDoesNotExist() {
        //when
        when(userRepository.findById(3L)).thenReturn(Optional.empty());

        //then
        assertThat(service.receiveUserById(3L)).isEqualTo(null);
    }

    @Test
    public void shouldReceiveUserByUsername() {
        //when
        when(userRepository.findByUsername("mockUser1")).thenReturn(Optional.of(user1));

        //then
        assertThat(service.receiveByUsername("mockUser1")).isEqualTo(user1);
    }

    @Test
    public void shouldThrowException_whenUsernameNotFound() {
        //when
        when(userRepository.findByUsername("mockUser3")).thenReturn(Optional.empty());

        //then
        assertThrows(UsernameNotFoundException.class, () -> service.receiveByUsername("mockUser3"));
    }

    @Test
    public void shouldValidatePasswordProperly() {
        //given
        String pass1 = "!12$A";
        String pass2 = "passwordABCD";
        String pass3 = "password123456password";
        String pass4 = "!PASSWORD123456";

        //expect
        assertThrows(InvalidCredentialsException.class, () -> service.changePassword(pass1));
        assertThrows(InvalidCredentialsException.class, () -> service.changePassword(pass2));
        assertThrows(InvalidCredentialsException.class, () -> service.changePassword(pass3));
        assertThrows(InvalidCredentialsException.class, () -> service.changePassword(pass4));
    }

    @Test
    public void shouldThrowException_whenWrongRole() {
        //when
        when(roleRepository.findByName("ROLE_BANNED")).thenReturn(Optional.empty());

        //then
        assertThrows(IllegalArgumentException.class, () -> service.addRole(1L, "ROLE_BANNED"));
    }
}
