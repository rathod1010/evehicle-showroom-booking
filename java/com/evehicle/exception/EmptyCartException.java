package com.evehicle.exception;

@SuppressWarnings("serial")
public class EmptyCartException extends RuntimeException{
	
	public EmptyCartException(String msg)
	{
		super(msg);
	}

}
