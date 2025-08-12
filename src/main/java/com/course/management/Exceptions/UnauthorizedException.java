package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

public class UnauthorizedException extends BaseException{
    public UnauthorizedException(String message)
    {
        super(message, HttpStatus.UNAUTHORIZED);
    }
}