package com.user.Exception;

public class MovieNotAvailableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public MovieNotAvailableException(String Exception) {
		super(Exception);
	}

}
