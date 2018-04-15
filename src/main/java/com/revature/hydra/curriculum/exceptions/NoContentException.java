package com.revature.hydra.curriculum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception representing an HTTP No Content status.
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends Exception{
    private static final long serialVersionUID = -1669307485951446112L;
    
    public NoContentException(String message) {
        super(message);
    }
}
