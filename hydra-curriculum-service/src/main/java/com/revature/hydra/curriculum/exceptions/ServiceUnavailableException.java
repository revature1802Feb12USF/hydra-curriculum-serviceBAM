package com.revature.hydra.curriculum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
public class ServiceUnavailableException extends Exception {

	private static final long serialVersionUID = 7687962326344794156L;
	
	/**
	 * Create an exception representing an unavailable service.
	 * @param message Custom message to store.
	 */
	public ServiceUnavailableException(String message) {
		super(message);
	}
}
