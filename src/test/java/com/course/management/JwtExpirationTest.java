package com.course.management;

import com.course.management.Security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  // <-- import get here
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class JwtExpirationTest {

    @Autowired
    private MockMvc mockMvc;

    private JwtUtil jwtUtil;

    @BeforeEach
    void setup() {
        // Use your real secret but set expiration to 1 second for testing
        jwtUtil = new JwtUtil("your-very-secret-key-which-should-be-long-enough", 1000);
    }

    @Test
    public void tokenExpiresAfterExpirationTime() throws Exception {
        String token = jwtUtil.generateToken("existingUser");

        // Immediately token should not be expired
        assertFalse(jwtUtil.isTokenExpired(token));

        // Wait 2 seconds to exceed expiration
        Thread.sleep(2000);

        // Now token should be expired
        assertTrue(jwtUtil.isTokenExpired(token));

        // Using mockMvc to access your secured endpoint with expired token should return 401
        mockMvc.perform(get("/api/test/secure")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }
}
