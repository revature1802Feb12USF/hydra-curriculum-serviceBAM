package com.revature.hydra.curriculum.pojos;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends Exception{
	
	// SHORTEN ME
	
	private static final long serialVersionUID = -8911611381170071078L;

	private final String message;

	public BadRequestException(String message) {
		this.message = message;
	}

	@Override
	public String getMessage() {
		return message;
	}
	
}
