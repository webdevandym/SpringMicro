package com.demo.web.rest.error;

import com.demo.service.exception.BadRequestException;
import com.demo.service.exception.ConflictRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionTranslator {

    @ExceptionHandler(ConflictRequestException.class)
    public ResponseEntity<RequestErrorVM> handleConflictRequestException(ConflictRequestException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(new RequestErrorVM(ex.getErrorCode(), ex.getErrorMessage()));
    }

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<RequestErrorVM> handleBadRequestException(BadRequestException ex) {
        return ResponseEntity.badRequest()
                             .body(new RequestErrorVM(ex.getErrorCode(), ex.getErrorMessage()));
    }
}
