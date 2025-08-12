package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class StudentNotFoundException extends BaseException {

    public StudentNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
