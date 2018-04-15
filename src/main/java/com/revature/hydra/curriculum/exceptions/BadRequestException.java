package com.revature.hydra.curriculum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for bad requests.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
    private static final long serialVersionUID = -8911611381170071078L;
    
    public BadRequestException(String message) {
        super(message);
    }
}
