package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ContextConfiguration
class UserRepositoryIntegrationTest {

    @Autowired TestEntityManager entityManager;
    @Autowired UserRepository repository;

    User user1;
    User user2;

    @BeforeEach
    void setUp() {
        user1 = User.builder()
                .username("first")
                .email("first@gmail.com")
                .password("pass123")
                .build();

        user2 = User.builder()
                .username("second")
                .email("second@gmail.com")
                .password("pass1234")
                .build();

        entityManager.persist(user1);
        entityManager.persist(user2);
        entityManager.flush();
    }

    @Test
    void injectedObjectsAreNotNull() {
        assertAll(
                () -> assertThat(entityManager).isNotNull(),
                () -> assertThat(repository).isNotNull()
        );
    }

    @Test
    void shouldFindUserByUsername() {
        //when
        User found1 = repository
                .findByUsername("first")
                .orElse(null);

        User found2 = repository
                .findByUsername("second")
                .orElse(null);

        //expect
        assertAll(
                () -> assertThat(found1).isEqualTo(user1),
                () -> assertThat(found2).isEqualTo(user2)
        );
    }

    @Test
    void checkIfUserExistsByUsername() {
        //when
        boolean exists1 = repository.existsByUsername("first");
        boolean exists2 = repository.existsByUsername("third");

        //expect
        assertTrue(exists1);
        assertFalse(exists2);
    }

    @Test
    void checkIfUserExistsByEmail() {
        //when
        boolean exists1 = repository.existsByEmail("second@gmail.com");
        boolean exists2 = repository.existsByUsername("third@gmail.com");

        //expect
        assertTrue(exists1);
        assertFalse(exists2);
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }
}