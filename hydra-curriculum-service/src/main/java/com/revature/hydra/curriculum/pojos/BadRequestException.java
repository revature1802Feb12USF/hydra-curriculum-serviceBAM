package com.revature.hydra.curriculum.pojos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception for bad requests.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
	
	private static final long serialVersionUID = -8911611381170071078L;

	private final String message;

	/**
	 * One argument constructor.
	 * 
	 * @param message Exception message
	 */
	public BadRequestException(String message) {
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
