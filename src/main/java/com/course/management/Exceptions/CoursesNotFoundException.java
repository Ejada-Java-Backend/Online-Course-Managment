package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class CoursesNotFoundException extends BaseException{

    public CoursesNotFoundException(String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }
}
