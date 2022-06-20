package com.evehicle.exception;

@SuppressWarnings("serial")
public class OrderNotFoundException extends RuntimeException {
	
	public OrderNotFoundException(String msg)
	{
		super(msg);
	}

}
