package com.course.management;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.course.management.Models.User;
import com.course.management.Repositories.UserRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @BeforeEach
    void setup() {
        // Clean up and insert user before each test
        userRepository.deleteAll();

        User user = User.builder()
                .username("existingUser")
                .email("existingUser@example.com")
                .password(encoder.encode("correctPassword")) // encode password to match security config
                .build();

        userRepository.save(user);
    }

    @Test
    public void loginWithValidUser_shouldReturnToken() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "existingUser")
                        .param("password", "correctPassword"))
                .andExpect(status().isOk())
                .andExpect(content().string(Matchers.notNullValue()))
                .andExpect(content().string(Matchers.matchesPattern("^([A-Za-z0-9-_]+)\\.([A-Za-z0-9-_]+)\\.([A-Za-z0-9-_]+)$")));
    }

    @Test
    public void loginWithInvalidUser_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "wrongUser")
                        .param("password", "wrongPass"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginWithEmptyUsername_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "")
                        .param("password", "somePass"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithEmptyPassword_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "existingUser")
                        .param("password", ""))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithSqlInjection_shouldReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "existingUser'; DROP TABLE users; --")
                        .param("password", "anything"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void loginWithMissingUsername_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("password", "correctPassword"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void loginWithMissingPassword_shouldReturnBadRequest() throws Exception {
        mockMvc.perform(post("/api/auth/login")
                        .param("username", "existingUser"))
                .andExpect(status().isBadRequest());
    }
}
