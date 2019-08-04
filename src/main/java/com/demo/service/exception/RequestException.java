package com.demo.service.exception;

abstract public class RequestException extends RuntimeException {
    private String errorMessage;
    private String errorCode;

    public RequestException(String errorCode, String errorMessage) {
        super(errorMessage);

        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
