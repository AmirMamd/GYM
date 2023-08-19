package com.example.gym.Exceptions;

import org.springframework.security.core.AuthenticationException;

public class NotFoundAuthenticatedUserException extends AuthenticationException {
    public NotFoundAuthenticatedUserException(String s) {
        super(s);
    }
}
