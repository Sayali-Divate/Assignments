package com.garageplug.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;



@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(LoginException.class)
	public ResponseEntity<ErrorDetails> loginException(LoginException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> exception(Exception ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDetails> noHandlerFoundException(NoHandlerFoundException ee, WebRequest req){
		
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ee.getMessage());
		error.setDescription(req.getDescription(false));
		
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_GATEWAY);
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorDetails> methodArguementNotValidException(MethodArgumentNotValidException ee){
		
		ErrorDetails error = new ErrorDetails();
		error.setTimeStamp(LocalDateTime.now());
		error.setMessage(ee.getMessage());
		error.setDescription(ee.getBindingResult().getFieldError().getDefaultMessage());
		
		return new ResponseEntity<ErrorDetails>(error, HttpStatus.BAD_REQUEST);
	}
	
}
