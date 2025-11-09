package org.example.commerce.annotation;

import org.example.commerce.exception.BusinessException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<String> handleException(final BusinessException be){

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(be.getClass(), ResponseStatus.class);
        if(responseStatus != null){
            return ResponseEntity.status(responseStatus.code()).body(be.getMessage());

        }


        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(be.getMessage());
    }
}
