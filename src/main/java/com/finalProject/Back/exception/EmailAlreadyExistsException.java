package com.finalProject.Back.exception;

public class EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String email) {
        super(email + "은 이미 사용중인 이메일입니다.");
    }
}