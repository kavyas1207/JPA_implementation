package com.sample.project.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BuyerAlreadyExists extends RuntimeException {

	public  BuyerAlreadyExists(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

}