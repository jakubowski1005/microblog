package com.jakubowskiartur.authservice.controller;

import org.codehaus.jackson.map.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

    private final Logger log = LoggerFactory.getLogger(UserControllerIntegrationTest.class);
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired private MockMvc mockMvc;

    @Test
    public void shouldNotReceiveListOfUsers_whenUnauthorized() throws Exception {

        mockMvc.perform(get("/users"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void shouldNotReceiveListOfUsers_whenRoleIsNotAdmin() throws Exception {
        String token = obtainAccessToken("user");

        mockMvc.perform(get("/users").header("Authorization", "Bearer " + token))
                .andExpect(status().isForbidden());
    }

    @Test
    public void shouldReceiveListOfUsers_whenRoleAdmin() throws Exception {
        String token = obtainAccessToken("admin");

        mockMvc.perform(get("/users")
                .header("Authorization", "Bearer " + token))

      .andExpect(status().isOk());

//        mockMvc.perform(get("/users")
//                .param("email", "jim@yahoo.com")
//                .header("Authorization", "Bearer " + accessToken)
//                .accept("application/json;charset=UTF-8"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType("application/json;charset=UTF-8"))
//      .andExpect(jsonPath("$.name", is("Jim")));
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

    //    @BeforeEach
//    public void setup() {
//
//        User admin = User.builder()
//                .username("admin")
//                .email("admin@gmail.com")
//                .password("{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG")
//                .roles(List.of(new Role(null, "ROLE_USER"), new Role(null, "ROLE_ADMIN")))
//                .enabled(true)
//                .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true)
//                .build();
//
//        User user = User.builder()
//                .username("user")
//                .email("user@gmail.com")
//                .password("{bcrypt}$2a$10$RqdCJE0jAc2J1dYdjMfr3eLIWc.OAXZZwpmxtIJqPSPPRc7J7NrmG")
//                .roles(List.of(new Role(null, "ROLE_USER")))
//                .enabled(true)
//                .accountNonExpired(true).accountNonLocked(true).credentialsNonExpired(true)
//                .build();
//
//        entityManager.persist(admin);
//        entityManager.persist(user);
//        entityManager.flush();
//    }
}
