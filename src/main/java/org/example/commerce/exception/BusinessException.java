package org.example.commerce.exception;

import lombok.Getter;

import java.io.Serializable;

@Getter
public abstract class BusinessException extends RuntimeException implements Serializable  {
    private static final Long serialVersionUID = 1L;

    public BusinessException(final String errorMessage){
        super(errorMessage);
    }
}
