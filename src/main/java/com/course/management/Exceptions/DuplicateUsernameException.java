package com.course.management.Exceptions;

public class DuplicateUsernameException extends RuntimeException{

    public DuplicateUsernameException(String message)
    {
        super(message);
    }
}
