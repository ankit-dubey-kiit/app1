package com.tracking.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorAPIResponse {
	private String message;
    private String status;

    public ErrorAPIResponse(String message, String status) {
        this.message = message;
        this.status = status;
    }
}
