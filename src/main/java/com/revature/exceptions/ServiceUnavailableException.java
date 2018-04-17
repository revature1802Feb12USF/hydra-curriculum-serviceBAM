package com.revature.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * An exception representing an exception to be thrown when a remote dependency is unavailable.
 */
@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends Exception {
    private static final long serialVersionUID = 7687962326344794156L;
    
    public ServiceUnavailableException(String message) {
        super(message);
    }
}
