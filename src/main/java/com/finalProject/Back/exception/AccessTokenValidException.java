package com.finalProject.Back.exception;

public class AccessTokenValidException extends RuntimeException{

    public AccessTokenValidException(String message) {
        super(message);
    }
}
