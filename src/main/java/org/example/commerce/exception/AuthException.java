package org.example.commerce.exception;

import java.io.Serial;
import java.io.Serializable;

public abstract class AuthException extends RuntimeException implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;
    private final String authMessage;

    public AuthException(String message,String authMessage) {
        super(message);
        this.authMessage = authMessage;
    }

    public String toWWWAuthenticateMessage(){
        return authMessage;
    }
}
