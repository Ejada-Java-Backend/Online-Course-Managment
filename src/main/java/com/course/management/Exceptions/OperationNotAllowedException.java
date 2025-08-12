package com.course.management.Exceptions;

public class OperationNotAllowedException extends RuntimeException{

    public OperationNotAllowedException(String message)
    {
        super(message);
    }
}
