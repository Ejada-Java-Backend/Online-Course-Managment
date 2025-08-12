package com.course.management.Exceptions;

public class AdminNotFoundException extends RuntimeException{

    public AdminNotFoundException(String message)
    {
        super(message);
    }
}
