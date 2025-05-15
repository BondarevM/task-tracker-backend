package com.bondarev.backend.exception;

public class InvalidTokenException extends RuntimeException{
    public InvalidTokenException(){
        super("Token is invalid or expired");
    }
}
