package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryIT {

    @Autowired UserRepository repository;

    @Test
    void shouldFindUserByUsername() {
        //given
        var user = User.builder()
                .id(11L)
                .username("test_user")
                .email("email@gmail.com")
                .password("{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG")
                .roles(null)
                .enabled(true)
                .credentialsNonExpired(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .build();

        //when
        User found1 = repository
                .findByUsername("test_user")
                .orElse(null);

        User found2 = repository
                .findByUsername("second_user")
                .orElse(null);

        //expect
        assertAll(
                () -> assertThat(found1).isEqualToIgnoringGivenFields(user, "roles"),
                () -> assertThat(found2).isEqualTo(null)
        );
    }

    @Test
    void checkIfUserExistsByUsername() {
        //when
        boolean exists1 = repository.existsByUsername("test_user");
        boolean exists2 = repository.existsByUsername("test_banned_user");

        //expect
        assertTrue(exists1);
        assertFalse(exists2);
    }

    @Test
    void checkIfUserExistsByEmail() {
        //when
        boolean exists1 = repository.existsByEmail("email@gmail.com");
        boolean exists2 = repository.existsByUsername("wrong@gmail.com");

        //expect
        assertTrue(exists1);
        assertFalse(exists2);
    }
}