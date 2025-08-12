package com.course.management.Exceptions;

import org.springframework.http.HttpStatus;

public class DuplicateUsernameException extends BaseException{

    public DuplicateUsernameException(String message)
    {
        super(message, HttpStatus.CONFLICT);
    }
}
