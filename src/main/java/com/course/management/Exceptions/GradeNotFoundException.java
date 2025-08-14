package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class GradeNotFoundException extends BaseException {
    public GradeNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
