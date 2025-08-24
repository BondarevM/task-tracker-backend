package com.bondarev.backend.exception.common;

import com.bondarev.backend.exception.TaskTrackerBackendException;

public class InvalidTokenException extends TaskTrackerBackendException {
    public InvalidTokenException(){
        super("Token is invalid or expired");
    }
}
