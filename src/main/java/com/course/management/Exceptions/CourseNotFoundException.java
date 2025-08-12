package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class CourseNotFoundException extends BaseException{

    public CourseNotFoundException(String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }
}
