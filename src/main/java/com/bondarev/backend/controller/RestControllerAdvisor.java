package com.bondarev.backend.controller;

import com.bondarev.backend.exception.InvalidTokenException;
import com.bondarev.backend.model.dto.ResponseExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerAdvisor {

    @ExceptionHandler(value = {InvalidTokenException.class})
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    public ResponseExceptionDTO handleInvalidTokenException(Exception e){
        return ResponseExceptionDTO.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(e.getMessage())
                .cause(e)
                .build();
    }
}
