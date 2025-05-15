package com.bondarev.backend.exception;

import java.text.MessageFormat;

public class EntityNotFoundException extends RuntimeException{
    public EntityNotFoundException(Class<?> entity, String message){
        super(MessageFormat.format("There is no {0} = {1}",entity.getSimpleName(), message));
    }
}
