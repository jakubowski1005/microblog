package com.jakubowskiartur.authservice.controller;

import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerIT {

    @Autowired private MockMvc mockMvc;
    @Autowired private PasswordEncoder encoder;

    @Test
    @Order(1)
    public void shouldNotReceiveListOfUsers_whenUnauthorized() throws Exception {
        mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(2)
    public void shouldNotReceiveListOfUsers_whenNotAdmin() throws Exception {
        mockMvc.perform(get("/users")
                    .header("Authorization", "Bearer " + obtainAccessToken("user")))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(3)
    public void shouldReceiveListOfUsers_whenRoleAdmin() throws Exception {
        mockMvc.perform(get("/users")
                    .header("Authorization", "Bearer " + obtainAccessToken("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(3)))
                .andExpect(jsonPath("$[0].id", is(1)))
                .andExpect(jsonPath("$[1].username", is("test_mod")))
                .andExpect(jsonPath("$[2].roles", hasSize(3)));
    }

    @Test
    @Order(4)
    public void shouldNotReceiveUserById_whenUnauthorized() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(5)
    public void shouldNotReceiveUserById_whenRoleIsNotAdmin() throws Exception {
        mockMvc.perform(get("/users/1")
                .header("Authorization", "Bearer " + obtainAccessToken("user")))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(6)
    public void shouldReceiveUserById_whenRoleAdmin() throws Exception {
        mockMvc.perform(get("/users/1")
                .header("Authorization", "Bearer " + obtainAccessToken("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.username", is("test_user")))
                .andExpect(jsonPath("$.roles", hasSize(1)));
    }

    @Test
    @Order(7)
    public void shouldNotReceiveUserByUsername_whenUnauthorized() throws Exception {
        mockMvc.perform(get("/users/username/test_mod"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @Order(8)
    public void shouldNotReceiveUserByUsername_whenRoleIsNotAdmin() throws Exception {
        mockMvc.perform(get("/users/username/test_mod")
                .header("Authorization", "Bearer " + obtainAccessToken("user")))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(9)
    public void shouldReceiveUserByUsername_whenRoleAdmin() throws Exception {
        mockMvc.perform(get("/users/username/test_mod")
                .header("Authorization", "Bearer " + obtainAccessToken("admin")))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(2)))
                .andExpect(jsonPath("$.username", is("test_mod")))
                .andExpect(jsonPath("$.roles", hasSize(2)));
    }

    @Test
    @Order(10)
    public void ifPrincipal_thenCanChangePassword() throws Exception {
        mockMvc.perform(put("/users/me")
                    .header("Authorization", "Bearer " + obtainAccessToken("user"))
                    .contentType("application/json;charset=UTF-8")
                    .content("Pass1234"))
                .andExpect(status().isOk());

        String body = mockMvc.perform(get("/users/1")
                    .header("Authorization", "Bearer " + obtainAccessToken("admin")))
                .andReturn()
                .getResponse()
                .getContentAsString();

        String password = JsonPath.read(body, "$.password");
        assertTrue(encoder.matches("Pass1234", password));
    }

    @Test
    @Order(11)
    public void ifAdmin_thenCanChangeUserRoles() throws Exception {
        String token = obtainAccessToken("admin");

        mockMvc.perform(put("/users/1")
                    .header("Authorization", "Bearer " + token)
                    .contentType("application/json;charset=UTF-8")
                    .content("ROLE_MOD"))
                .andExpect(status().isOk());

        mockMvc.perform(get("/users/1")
                    .header("Authorization", "Bearer " + token))
                .andExpect(jsonPath("$.roles", hasSize(2)))
                .andExpect(jsonPath("$.roles[1].name", is("ROLE_MOD")));
    }

    @Test
    @Order(12)
    public void ifNotAdmin_thenCannotChangeUserRoles() throws Exception {
        mockMvc.perform(put("/users/1")
                    .header("Authorization", "Bearer " + obtainAccessToken("mod"))
                    .contentType("application/json;charset=UTF-8")
                    .content("ROLE_ADMIN"))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(13)
    public void ifNotAdmin_thenCannotDeleteAccountById() throws Exception {
        mockMvc.perform(delete("/users/1")
                .header("Authorization", "Bearer " + obtainAccessToken("mod")))
                .andExpect(status().isForbidden());
    }

    @Test
    @Order(14)
    public void ifPrincipal_thenCanDeleteAccount() throws Exception {
        mockMvc.perform(delete("/users/me")
                    .header("Authorization", "Bearer " + obtainAccessToken("mod")))
                .andExpect(status().isOk());
    }

    @Test
    @Order(15)
    public void ifAdmin_thenCanDeleteAccountById() throws Exception {
        mockMvc.perform(delete("/users/1")
                .header("Authorization", "Bearer " + obtainAccessToken("admin")))
                .andExpect(status().isOk());
    }

    private String obtainAccessToken(String role) throws Exception {

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "password");
        params.add("client_id", "mobile");
        params.add("username", "test_" + role);
        params.add("password", "pass123");

        ResultActions result
                = mockMvc.perform(post("/oauth/token")
                .params(params)
                .with(httpBasic("mobile","pin"))
                .accept("application/json;charset=UTF-8"))
                .andExpect(status().isOk())
                .andExpect(content().contentType("application/json;charset=UTF-8"));

        String resultString = result
                .andReturn()
                .getResponse()
                .getContentAsString();

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(resultString).get("access_token").toString();
    }
}