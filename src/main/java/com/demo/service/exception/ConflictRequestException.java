package com.demo.service.exception;

public class ConflictRequestException extends RequestException {
    public ConflictRequestException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
