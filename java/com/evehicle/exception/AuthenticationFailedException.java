package com.evehicle.exception;

public class AuthenticationFailedException extends RuntimeException {

	public AuthenticationFailedException(String msg) {
		super(msg);
	}
}