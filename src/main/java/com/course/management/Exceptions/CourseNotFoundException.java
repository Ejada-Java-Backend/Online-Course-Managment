package com.course.management.Exceptions;

public class CourseNotFoundException extends RuntimeException{

    public CourseNotFoundException(String message)
    {
        super(message);
    }
}
