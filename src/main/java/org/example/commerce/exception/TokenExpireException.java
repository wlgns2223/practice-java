package org.example.commerce.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class TokenExpireException extends AuthException {
    public TokenExpireException(String message,String authMessage) {
        super(message,authMessage);
    }

}
