package com.ss.lms.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler{
 
	@ExceptionHandler
	public ResponseEntity<ApplicationErrors> notFoundException(NotFoundException e , WebRequest webRequest){
		ApplicationErrors error = new ApplicationErrors(e.getMessage(),"404");
		return new ResponseEntity<>(error,HttpStatus.NOT_FOUND);
	}
}
