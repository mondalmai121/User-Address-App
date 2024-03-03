package org.jsp.useraddressbootapp.exception;

public class InvalidCredentialsException extends RuntimeException{

	public InvalidCredentialsException(String message) {
		super(message);
	}
	
	public InvalidCredentialsException() {
		//No argument 
	}
	
}
