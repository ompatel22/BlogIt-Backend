package com.spring.blogitbackend.exceptions;

public class ResourceNotFoundException extends RuntimeException {

    String resouceName;
    String fieldName;
    Long fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, Long fieldValue) {
        super(String.format("Resource %s not found with field %s = %s", resourceName, fieldName, fieldValue));
        this.resouceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
