package com.java.springmongo.services.exception;

public class ObjectNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ObjectNotFoundException() {
		
	}
	
	public ObjectNotFoundException(String msg)
	{
		super(msg);
	}

}
