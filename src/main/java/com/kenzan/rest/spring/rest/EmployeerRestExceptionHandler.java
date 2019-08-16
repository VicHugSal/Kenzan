package com.kenzan.rest.spring.rest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class EmployeerRestExceptionHandler {

	// Add an exception handler for EmployeeNotFoundException
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(EmployeeNotFoundException exc){
		
		// create EmployeeErrorResponse
		
		EmployeeErrorResponse error= new EmployeeErrorResponse(
											HttpStatus.NOT_FOUND.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		// return Responsibility
		
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler
	public ResponseEntity<EmployeeErrorResponse> handleException(Exception exc){
		
		// create EmployeeErrorResponse
		
		EmployeeErrorResponse error= new EmployeeErrorResponse(
											HttpStatus.BAD_REQUEST.value(),
											exc.getMessage(),
											System.currentTimeMillis());
		// return Responsibility
		
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
	}
	
		
}
