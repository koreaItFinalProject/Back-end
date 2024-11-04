package com.finalProject.Back.exception;

public class Oauth2NameAlreadyExistsException extends RuntimeException {

    public Oauth2NameAlreadyExistsException(String oauth2Name) {
        super(oauth2Name + "은 이미 사용중인 아이디입니다.");
    }
}