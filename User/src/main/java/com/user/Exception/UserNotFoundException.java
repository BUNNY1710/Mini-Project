package com.user.Exception;

public class UserNotFoundException extends RuntimeException {
private static final long serialVersionUID = 1L;
	
	public UserNotFoundException(String Exception)
	{
		super(Exception);
	}
}
