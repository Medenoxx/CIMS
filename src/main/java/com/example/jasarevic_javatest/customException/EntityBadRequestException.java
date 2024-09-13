package com.example.jasarevic_javatest.customException;


public class EntityBadRequestException extends RuntimeException {
    public EntityBadRequestException(String message) {
        super(message);
    }
}
