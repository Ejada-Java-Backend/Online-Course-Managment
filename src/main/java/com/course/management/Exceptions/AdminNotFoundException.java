package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class AdminNotFoundException extends BaseException{

    public AdminNotFoundException(String message)
    {
        super(message, HttpStatus.NOT_FOUND);
    }
}
