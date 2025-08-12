package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class Exception extends BaseException{
    public Exception(String message)
    {
        super(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}