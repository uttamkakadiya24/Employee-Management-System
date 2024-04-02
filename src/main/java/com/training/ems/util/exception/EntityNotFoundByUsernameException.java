package com.training.ems.util.exception;

public class EntityNotFoundByUsernameException extends RuntimeException{
    public EntityNotFoundByUsernameException(String entityName, String username) {
        super(entityName + " not found by username: " + username);
    }
}
