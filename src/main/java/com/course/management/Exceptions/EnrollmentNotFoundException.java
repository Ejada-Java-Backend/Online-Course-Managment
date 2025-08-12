package com.course.management.Exceptions;

public class EnrollmentNotFoundException extends RuntimeException{

    public EnrollmentNotFoundException(String message)
    {
        super(message);
    }
}
