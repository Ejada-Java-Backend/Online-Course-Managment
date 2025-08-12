package com.course.management.Exceptions;

public class NotFoundException extends RuntimeException{

    public NotFoundException(String message)
    {
        super(message);
    }
}
