package com.example.gym.Exceptions;

import org.springframework.http.HttpStatus;

public class NotFoundUserException extends RuntimeException {
    private HttpStatus httpStatus;
    private String message;
    public NotFoundUserException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus=httpStatus;
        this.message=message;

    }
    public HttpStatus httpStatus(){
        return httpStatus;
    }

    @Override
    public String getMessage(){
        return message;
    }

}
