package com.course.management.Exceptions;

public class ReviewNotFoundException extends RuntimeException{

    public ReviewNotFoundException(String message)
    {
        super(message);
    }
}
