package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.Role;
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
class RoleRepositoryIT {

    @Autowired TestEntityManager entityManager;
    @Autowired RoleRepository repository;

    Role firstRole;
    Role secondRole;

    @BeforeEach
    void setUp() {
        firstRole = new Role(null, "ROLE_TESTER");
        secondRole = new Role(null, "ROLE_QA");

        entityManager.persist(firstRole);
        entityManager.persist(secondRole);
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
    void shouldFindRoleByItsName() {
        //when
        Role found1 = repository
                .findByName(firstRole.getName())
                .orElse(null);

        Role found2 = repository
                .findByName(secondRole.getName())
                .orElse(null);

        //expect
        assertAll(
                () -> assertThat(found1).isEqualTo(firstRole),
                () -> assertThat(found2).isEqualTo(secondRole)
        );
    }

    @AfterEach
    void tearDown() {
        entityManager.clear();
    }
}