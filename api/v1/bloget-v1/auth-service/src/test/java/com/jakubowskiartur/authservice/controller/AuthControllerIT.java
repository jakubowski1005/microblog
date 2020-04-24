package com.jakubowskiartur.authservice.controller;

import com.jakubowskiartur.authservice.service.SignUpRequest;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthControllerIT {

    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper mapper = new ObjectMapper();

    @Test
    public void usernameShouldBeBetween3And100Chars() throws Exception {
        String login = "me";
        String email = "buziaczek@onet.pl";
        String password = "Pass1234";
        SignUpRequest request = new SignUpRequest(login, email, password);
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/register")
                    .contentType("application/json;charset=UTF-8")
                    .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void emailShouldBeValid() throws Exception {
        String login = "mewtoo123";
        String email = "buziaczek";
        String password = "Pass1234";
        SignUpRequest request = new SignUpRequest(login, email, password);
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/register")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void passwordShouldBeBetween3And100Chars() throws Exception {
        String login = "mewtwoo123";
        String email = "buziaczek@onet.pl";
        String password = "pass";
        SignUpRequest request = new SignUpRequest(login, email, password);
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/register")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void fieldsCannotBeEmpty() throws Exception {
        String login = "";
        String email = "";
        String password = "";
        SignUpRequest request = new SignUpRequest(login, email, password);
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/register")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void shouldRegisterCorrectly() throws Exception {
        String login = "mewtwoo";
        String email = "buziaczek@onet.pl";
        String password = "Pass1234";
        SignUpRequest request = new SignUpRequest(login, email, password);
        String json = mapper.writeValueAsString(request);

        mockMvc.perform(post("/register")
                .contentType("application/json;charset=UTF-8")
                .content(json))
                .andExpect(status().isBadRequest());
    }
}