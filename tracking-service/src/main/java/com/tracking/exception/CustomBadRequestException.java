package com.tracking.exception;

import lombok.Data;

@Data
public class CustomBadRequestException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	private String message;
	private String status;
	
	public CustomBadRequestException(String message) {
		super(message);
		this.status = "FAILED";
		this.message = message;
	}
	
	public CustomBadRequestException(String message,String status) {
		super(message);
		this.status = status;
		
    }
}
