package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class EnrollmentNotFoundException extends BaseException{

    public EnrollmentNotFoundException(String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }
}
