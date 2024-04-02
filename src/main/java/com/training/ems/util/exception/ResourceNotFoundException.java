package com.training.ems.util.exception;

import lombok.experimental.StandardException;

@StandardException
public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String message){
        super(message);
    }
}
