package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception representing an HTTP No Content status.
 * 
 * @see HttpStatus#NO_CONTENT
 * 
 * @author Unknown
 * @author Ricky Baker (1802-Matt)
 * @author Seth Maize (1802-Matt)
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends Exception {
    private static final long serialVersionUID = -1669307485951446112L;

    public NoContentException(String message) {
        super(message);
    }
}
