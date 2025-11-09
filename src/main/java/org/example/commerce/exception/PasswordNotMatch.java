package org.example.commerce.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PasswordNotMatch extends BusinessException {
    public PasswordNotMatch(String message) {
        super(message);
    }
}
