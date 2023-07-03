package com.theatre.Exception;

public class TheatreNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public TheatreNotFoundException(String Exception)
	{
		super(Exception);
	}
}
