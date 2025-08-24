package com.bondarev.backend.exception.common;

import com.bondarev.backend.exception.TaskTrackerBackendException;

public class EntityAlreadyExistsException extends TaskTrackerBackendException {
    public EntityAlreadyExistsException(String message) {
        super(message);
    }
}
