package com.course.management.Security;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Temporary: hardcoded user until DB is ready
        if ("testuser".equals(username)) {
            return new User(
                    "testuser",
                    "{noop}password", // {noop} means no password encoding for now
                    Collections.emptyList() // No roles yet
            );
        }
        throw new UsernameNotFoundException("User not found: " + username);
    }
}
