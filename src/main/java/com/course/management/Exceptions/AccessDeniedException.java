package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class AccessDeniedException extends BaseException{
    public AccessDeniedException(String message) {
        super(message, HttpStatus.FORBIDDEN);
    }
}