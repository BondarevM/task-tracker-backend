package com.bondarev.backend.exception.common;

import com.bondarev.backend.exception.TaskTrackerBackendException;

import java.text.MessageFormat;

public class EntityNotFoundException extends TaskTrackerBackendException {
    public EntityNotFoundException(Class<?> entity, String message){
        super(MessageFormat.format("There is no {0} = {1}",entity.getSimpleName(), message));
    }
}
