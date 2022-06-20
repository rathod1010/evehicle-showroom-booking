package com.evehicle.exception;

@SuppressWarnings("serial")
public class CartNotFoundException extends RuntimeException {
	
	public CartNotFoundException(String msg)
	{
		super(msg);
	}

}
