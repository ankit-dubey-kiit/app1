package com.tracking.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.tracking.controller.TrackingController;

@RestControllerAdvice(assignableTypes = { TrackingController.class })
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class CustomExceptionHandler {

	@ExceptionHandler({CustomBadRequestException.class})
    public ResponseEntity<ErrorAPIResponse> handelOrderException(CustomBadRequestException exception){
		ErrorAPIResponse errorResponse = new ErrorAPIResponse(exception.getMessage(), exception.getStatus());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}
     
}
