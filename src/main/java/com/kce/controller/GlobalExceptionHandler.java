package com.kce.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import com.kce.model.ErrorDetails;
import com.kce.utils.FlavorNotFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {
     
	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> handleGlobalException(Exception ex, WebRequest request){
		ErrorDetails error = new ErrorDetails(new Date(), request.getDescription(false), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(FlavorNotFoundException.class)
	public ResponseEntity<?> handleFlavorNotFoundException(Exception ex, WebRequest request){
		ErrorDetails error = new ErrorDetails(new Date(), request.getDescription(false), ex.getMessage());
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.NOT_FOUND);
	}
}
