package com.theatre.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MovieNotFoundException.class)
	public ResponseEntity<String> handleMovieNotFoundException(MovieNotFoundException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
	
	@ExceptionHandler(TheatreNotFoundException.class)
	public ResponseEntity<String> handleTheatreNotFoundException(TheatreNotFoundException exception)
	{
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
	}
}
