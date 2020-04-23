package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.model.User;
import com.jakubowskiartur.authservice.service.UserService;
import com.jakubowskiartur.authservice.service.UserServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@ContextConfiguration
class UserControllerTest {

    @Autowired MockMvc mockMvc;
    ObjectMapper mapper = new ObjectMapper();
    @MockBean
    UserServiceImpl service;

    User user1;
    User user2;
    List<User> users;
    HttpHeaders headers;

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

        users = new ArrayList<>();
        users.add(user1);
        users.add(user2);

        headers = new HttpHeaders();
        headers.setBasicAuth("mobile", "pin");
    }

    @Test
    void shouldReturnListOfUsers() throws Exception {
        //when
        when(service.receiveUsers()).thenReturn(users);

        //expect
        mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content().json(mapper.writeValueAsString(users)));
    }

    @Test
    void shouldReturnUserById() {
    }

    @Test
    void shouldReturnUserByUsername() {
    }

    @Test
    void shouldChangeUserPassword() {
    }

    @Test
    void shouldChangeUserRoles() {
    }

    @Test
    void shouldDeletePrincipalAccount() {
    }

    @Test
    void shouldDeleteUserById() {
    }

    @AfterEach
    void tearDown() {
    }
}