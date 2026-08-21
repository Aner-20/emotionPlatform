package com.example.emotionPlatform.exception;

// Esempi: email già registrata, emozione con stesso nome, username duplicato

public class ResourceAlreadyExistsException extends RuntimeException {
    
    public ResourceAlreadyExistsException(String message){
        super(message);
    }
}
