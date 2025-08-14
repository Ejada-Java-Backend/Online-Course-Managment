package com.course.management.Security;

import com.course.management.Exceptions.UserNotFoundException;
import com.course.management.Models.Admin;
import com.course.management.Models.Student;
import com.course.management.Models.User;
import com.course.management.Repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UserNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException("User not found: " + username));

        String role;
        if (user instanceof Admin) {
            role = "ADMIN";
        } else if (user instanceof Student) {
            role = "STUDENT";
        } else {
            role = "USER";
        }

        return org.springframework.security.core.userdetails.User
                .withUsername(user.getUsername())
                .password(user.getPassword())
                .roles(role)
                .build();

    }
}
