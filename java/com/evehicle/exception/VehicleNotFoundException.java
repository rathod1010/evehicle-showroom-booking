package com.evehicle.exception;

@SuppressWarnings("serial")
public class VehicleNotFoundException extends RuntimeException {
	
	public VehicleNotFoundException(String msg)
	{
		super(msg);
	}

}
