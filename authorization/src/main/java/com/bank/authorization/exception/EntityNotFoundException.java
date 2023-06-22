package com.bank.authorization.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String className, Long id) {
        super(String.format("%s with id = %s not found", className, id));
    }
    public EntityNotFoundException(String className, Long id, RuntimeException e) {
        super(String.format("%s with id = %s not found", className, id), e);
    }

    public EntityNotFoundException(String className) {
        super(String.format("%s not found", className));
    }
}
