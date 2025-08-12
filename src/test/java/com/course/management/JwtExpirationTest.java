package com.course.management;

import com.course.management.Security.JwtUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;  // <-- import get here
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = {
        "jwt.secret=your-very-secret-key-which-should-be-long-enough",
        "jwt.expiration-ms=1000"
})
public class JwtExpirationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JwtUtil jwtUtil;

    @Test
    public void tokenExpiresAfterExpirationTime() throws Exception {
        String token = jwtUtil.generateToken("existingUser");

        // Immediately after generation, token should NOT be expired
        assertFalse(jwtUtil.isTokenExpired(token));

        // Wait 2 seconds so token expiration is passed
        Thread.sleep(2000);

        // Now token should be expired
        assertTrue(jwtUtil.isTokenExpired(token));

        // Accessing secured endpoint with expired token returns 401 Unauthorized
        mockMvc.perform(get("/api/test/secure")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isUnauthorized());
    }
}
