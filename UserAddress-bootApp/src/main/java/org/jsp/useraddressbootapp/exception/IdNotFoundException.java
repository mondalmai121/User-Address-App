package org.jsp.useraddressbootapp.exception;

public class IdNotFoundException extends RuntimeException{

	public String getMessage() {
		return "Invalid id";
	}
}
