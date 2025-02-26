package com.spring.blogitbackend.exceptions;

public class ImageUploadException extends RuntimeException {
    String message;
    public ImageUploadException(String message) {
        super(message);
        this.message = message;
    }
}
