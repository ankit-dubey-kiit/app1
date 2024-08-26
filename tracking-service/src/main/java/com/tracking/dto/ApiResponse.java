package com.tracking.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse {

private static final long serialVersionUID = 1L;
	
	private String status;
	TrackingNumberResponse response;
}
