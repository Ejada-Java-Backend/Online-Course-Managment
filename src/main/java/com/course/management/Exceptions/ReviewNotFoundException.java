package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class ReviewNotFoundException extends BaseException{

    public ReviewNotFoundException(String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }
}
