package com.course.management.Exceptions;

public class UnauthorizedException extends RuntimeException{

    public UnauthorizedException(String message)
    {
        super(message);
    }
}
