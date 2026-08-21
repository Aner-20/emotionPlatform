package com.example.emotionPlatform.exception;

// Se un utente non ha i permessi

public class UnAuthorizedException extends RuntimeException {
    
    public UnAuthorizedException(String message){
        super(message);
    }
}
