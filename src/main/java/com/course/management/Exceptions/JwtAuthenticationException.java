package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class JwtAuthenticationException extends BaseException {
    public JwtAuthenticationException(String message) {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}
