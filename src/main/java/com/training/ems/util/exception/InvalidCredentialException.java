package com.training.ems.util.exception;

import org.springframework.security.authentication.BadCredentialsException;

public class InvalidCredentialException extends BadCredentialsException {
    public InvalidCredentialException(String msg) {
        super(msg);
    }
}
