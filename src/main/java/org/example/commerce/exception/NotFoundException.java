package org.example.commerce.exception;

import org.springframework.http.HttpStatus;

public class NotFoundException extends BusinessException{

    public NotFoundException(final String errorMessage){
        super(HttpStatus.NOT_FOUND, errorMessage);
    }
}
