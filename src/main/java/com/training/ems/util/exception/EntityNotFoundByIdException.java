package com.training.ems.util.exception;

public class EntityNotFoundByIdException extends RuntimeException {
    public EntityNotFoundByIdException(String entityName, String id) {
        super(entityName + " not found by id: " + id);
    }
}
