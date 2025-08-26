package com.bondarev.backend.exception.common;

import com.bondarev.backend.exception.TaskTrackerBackendException;

public class InvalidUserInfoException extends TaskTrackerBackendException {
    public InvalidUserInfoException(String message) {
        super(message);
    }
}
