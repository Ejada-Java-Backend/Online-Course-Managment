package com.course.management.Exceptions;

public class InvalidRequestException extends RuntimeException{

    public InvalidRequestException(String message)
    {
        super(message);
    }
}
