package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class OperationNotAllowedException extends BaseException{

    public OperationNotAllowedException(String message)
    {
        super(message, HttpStatus.FORBIDDEN);
    }
}
