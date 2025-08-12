package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends BaseException{
    public IllegalArgumentException(String message) {
        super(message, HttpStatus.BAD_REQUEST);
    }
}