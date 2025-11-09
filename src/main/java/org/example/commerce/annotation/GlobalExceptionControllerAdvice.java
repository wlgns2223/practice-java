package org.example.commerce.annotation;

import org.example.commerce.exception.AuthException;
import org.example.commerce.exception.BusinessException;
import org.example.commerce.exception.TokenExpireException;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpHeaders;
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

    @ExceptionHandler(AuthException.class)
    public ResponseEntity<String> handleException(final AuthException e){

        ResponseStatus responseStatus = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        if(responseStatus != null){

            return ResponseEntity
                    .status(responseStatus.code())
                    .header(HttpHeaders.WWW_AUTHENTICATE, e.toWWWAuthenticateMessage())
                    .body(e.getMessage());

        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
}
