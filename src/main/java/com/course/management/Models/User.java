package com.course.management.Models;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Setter @Getter @ToString @NoArgsConstructor @AllArgsConstructor @SuperBuilder
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;
    private String email;
    private String password;

    @PrePersist
    @PreUpdate
    private void encodePasswordBeforeSave() {
        // Only encode if it's not already encoded (avoid double hashing)
        if (password != null && !password.startsWith("$2a$")) {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            this.password = encoder.encode(this.password);
        }
    }

    public String getUsername() { return username; }
    public String getPassword() { return password; }
  }
