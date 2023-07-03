package com.user.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<String> handleMovieNotFoundException(UserNotFoundException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(MovieNotAvailableException.class)
	public ResponseEntity<String> handleMovieNotAvailableException(MovieNotAvailableException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(exception.getMessage());
	}
	
}
