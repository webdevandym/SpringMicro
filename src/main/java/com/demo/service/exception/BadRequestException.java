package com.demo.service.exception;

public class BadRequestException extends RequestException {
    public BadRequestException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}
