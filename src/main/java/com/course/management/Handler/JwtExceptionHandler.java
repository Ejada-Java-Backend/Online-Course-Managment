package com.course.management.Handler;

import com.course.management.Exceptions.JwtTokenExpiredException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Map;

@ControllerAdvice
public class JwtExceptionHandler {

    @ExceptionHandler(JwtTokenExpiredException.class)
    public ResponseEntity<Map<String, String>> handleJwtTokenExpired(JwtTokenExpiredException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("error", "Token Expired", "message", ex.getMessage()));
    }
}
