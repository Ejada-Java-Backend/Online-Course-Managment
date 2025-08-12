package com.course.management.Exceptions;

public class DuplicateResourceException extends RuntimeException{

    public DuplicateResourceException(String message)
    {
        super(message);
    }
}
