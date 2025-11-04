package org.example.commerce.annotation;

import org.example.commerce.exception.BusinessException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(final BusinessException be){
        return ResponseEntity.status(be.getHttpStatus()).body(be.getMessage());
    }
}
