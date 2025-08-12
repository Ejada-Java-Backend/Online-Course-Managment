package com.course.management;

import com.course.management.Models.User;
import com.course.management.Repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserRepositoryTest{

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
                .password("password") // password will be encoded automatically
                .build();
        userRepository.save(user);

        long count = userRepository.count();
        System.out.println("Users in DB after insert: " + count);
        assertTrue(count > 0);
    }

    @Test
    public void testFindByUsername() {
        User user = User.builder()
                .username("findme")
                .email("findme@example.com")
                .password("password")
                .build();
        userRepository.save(user);

        var foundUserOpt = userRepository.findByUsername("findme");
        System.out.println("Users in DB after insert: " + foundUserOpt.toString());
        assertTrue(foundUserOpt.isPresent());
        assertEquals("findme@example.com", foundUserOpt.get().getEmail());
    }

    @Test
    public void testPasswordIsEncoded() {
        User user = User.builder()
                .username("encodeTest")
                .email("encode@example.com")
                .password("plaintext")
                .build();
        userRepository.save(user);

        var savedUser = userRepository.findByUsername("encodeTest").get();
        assertNotEquals("plaintext", savedUser.getPassword());
        assertTrue(savedUser.getPassword().startsWith("$2a$")); // BCrypt encoded password prefix
    }

    @Test
    public void testDuplicateUsernameFails() {
        User user1 = User.builder()
                .username("duplicate")
                .email("dup1@example.com")
                .password("password")
                .build();

        User user2 = User.builder()
                .username("duplicate")
                .email("dup2@example.com")
                .password("password")
                .build();

        userRepository.save(user1);

        Exception exception = assertThrows(Exception.class, () -> {
            userRepository.saveAndFlush(user2); // flush to force constraint check
        });

        System.out.println("Exception message: " + exception.getMessage());
    }

    @Test
    public void testDeleteUser() {
        User user = User.builder()
                .username("todelete")
                .email("todelete@example.com")
                .password("password")
                .build();
        userRepository.save(user);

        userRepository.delete(user);

        var foundUser = userRepository.findByUsername("todelete");
        assertTrue(foundUser.isEmpty());
    }

    @Test
    public void testUpdateUser() {
        User user = User.builder()
                .username("toupdate")
                .email("toupdate@example.com")
                .password("password")
                .build();
        userRepository.save(user);

        User savedUser = userRepository.findByUsername("toupdate").get();
        savedUser.setEmail("updated@example.com");
        userRepository.save(savedUser);

        User updatedUser = userRepository.findByUsername("toupdate").get();
        assertEquals("updated@example.com", updatedUser.getEmail());
    }
}
