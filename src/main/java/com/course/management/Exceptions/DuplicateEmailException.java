package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateEmailException extends BaseException{

    public DuplicateEmailException(String message)
    {
        super(message, HttpStatus.CONFLICT);
    }
}
