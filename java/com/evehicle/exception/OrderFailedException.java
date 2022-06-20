package com.evehicle.exception;

@SuppressWarnings("serial")
public class OrderFailedException extends RuntimeException {
	
	public OrderFailedException(String msg)
	{
		super(msg);
	}

}
