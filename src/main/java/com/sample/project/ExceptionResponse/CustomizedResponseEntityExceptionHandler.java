package com.sample.project.ExceptionResponse;



import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
@RestController

public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException
	        (Exception ex, WebRequest request){
		 ExceptionResponse exceptionresponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		 ResponseEntity<Object> responseEntity =  new ResponseEntity<Object>(exceptionresponse, HttpStatus.NOT_FOUND);
		 return responseEntity;
	
		
	}
	@ExceptionHandler(BuyerNotFoundException.class)
	public final ResponseEntity<Object> handleBuyerNotFoundException
	        (BuyerNotFoundException ex, WebRequest request){
		 ExceptionResponse exceptionresponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		 ResponseEntity<Object> responseEntity =  new ResponseEntity<Object>(exceptionresponse, HttpStatus.NOT_FOUND);
		 return responseEntity;
		
	}
	@ExceptionHandler(BuyerAlreadyExists.class)
	public final ResponseEntity<Object> handleBuyerAlreadyExists
	        (BuyerAlreadyExists ex, WebRequest request){
		 ExceptionResponse exceptionresponse=new ExceptionResponse(new Date(),ex.getMessage(),request.getDescription(false));
		 ResponseEntity<Object> responseEntity=new ResponseEntity<Object>(exceptionresponse,HttpStatus.BAD_REQUEST);
		 return responseEntity;
	}
	
	

}
