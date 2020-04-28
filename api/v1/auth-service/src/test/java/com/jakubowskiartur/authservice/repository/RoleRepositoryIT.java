package com.jakubowskiartur.authservice.repository;

import com.jakubowskiartur.authservice.model.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@DataJpaTest
@ContextConfiguration
class RoleRepositoryIT {

    @Autowired RoleRepository repository;

    @Test
    void shouldFindRoleByItsName() {
        //given
        var role = new Role(2L, "ROLE_MOD");

        //when
        Role found1 = repository
                .findByName("ROLE_MOD")
                .orElse(null);

        Role found2 = repository
                .findByName("ROLE_BANNED")
                .orElse(null);

        //then
        assertAll(
                () -> assertThat(found1).isEqualTo(role),
                () -> assertThat(found2).isEqualTo(null)
        );
    }
}