package org.example.commerce.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
public abstract class BusinessException extends RuntimeException implements Serializable  {
    private static final Long serialVersionUID = 1L;

    private HttpStatus httpStatus;

    public BusinessException(final HttpStatus httpStatus,final String errorMessage){
        super(errorMessage);
        this.httpStatus = httpStatus;
    }
}
