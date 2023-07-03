package com.theatre.Exception;

public class MovieNotFoundException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MovieNotFoundException(String Exception)
	{
		super(Exception);
	}
}
