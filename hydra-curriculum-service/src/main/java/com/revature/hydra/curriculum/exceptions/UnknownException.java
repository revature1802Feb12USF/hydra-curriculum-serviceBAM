package com.revature.hydra.curriculum.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
public class UnknownException extends RuntimeException {
	private static final long serialVersionUID = 6200481395864170286L;

	public UnknownException(String message) {
		super(message);
	}
}
