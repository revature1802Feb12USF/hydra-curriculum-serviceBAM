package com.revature.hydra.curriculum.beans;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for no content.
 */
@ResponseStatus(HttpStatus.NO_CONTENT)
public class NoContentException extends Exception{

	private static final long serialVersionUID = -1669307485951446112L;

	private final String message;

	/**
	 * One argument constructor.
	 * 
	 * @param message Exception message
	 */
	public NoContentException(String message) {
		this.message = message;
	}

	/**
	 * Returns exception message.
	 */
	@Override
	public String getMessage() {
		return message;
	}
}
