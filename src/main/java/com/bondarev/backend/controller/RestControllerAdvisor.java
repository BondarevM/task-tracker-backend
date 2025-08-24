package com.bondarev.backend.controller;

import com.bondarev.backend.exception.common.EntityAlreadyExistsException;
import com.bondarev.backend.exception.common.EntityNotFoundException;
import com.bondarev.backend.exception.common.InvalidTokenException;
import com.bondarev.backend.model.dto.TaskTrackerBackendExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestControllerAdvisor {
    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public TaskTrackerBackendExceptionDTO handleException(Exception e) {
        return TaskTrackerBackendExceptionDTO.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {InvalidTokenException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public TaskTrackerBackendExceptionDTO handleInvalidTokenException(InvalidTokenException e) {
        return TaskTrackerBackendExceptionDTO.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {EntityAlreadyExistsException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public TaskTrackerBackendExceptionDTO handleEntityAlreadyExistsException(EntityAlreadyExistsException e) {
        return TaskTrackerBackendExceptionDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {EntityNotFoundException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public TaskTrackerBackendExceptionDTO handleEntityNotFoundException(EntityNotFoundException e) {
        return TaskTrackerBackendExceptionDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(e.getMessage())
                .build();
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public TaskTrackerBackendExceptionDTO handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        Map<String, String> errors = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return TaskTrackerBackendExceptionDTO.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .props(errors)
                .build();
    }
}
