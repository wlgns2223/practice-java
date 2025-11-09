package org.example.commerce.exception;

import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;

@Getter
public abstract class BusinessException extends RuntimeException implements Serializable  {
    @Serial
    private static final long serialVersionUID = 1L;

    public BusinessException(final String errorMessage){
        super(errorMessage);
    }
}
