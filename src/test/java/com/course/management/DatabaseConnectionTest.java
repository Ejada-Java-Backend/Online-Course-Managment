package com.course.management;

import com.course.management.Models.User;
import com.course.management.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class DatabaseConnectionTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testUserCount() {
        long count = userRepository.count();
        System.out.println("Users in DB: " + count);
        assertTrue(count >= 0);
    }

    @Test
    public void testInsertUser() {
        User user = User.builder()
                .username("testuser")
                .email("test@example.com")
                .password("password") // normally encode password!
                .build();
        userRepository.save(user);

        long count = userRepository.count();
        System.out.println("Users in DB after insert: " + count);
        assertTrue(count > 0);
    }

}
