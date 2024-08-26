package com.tracking.dto;

import java.io.Serializable;
import java.util.Date;

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
public class TrackingNumberResponse implements Serializable{

	private static final long serialVersionUID = 1L;

	private Date createdAt;
	private String trackingNumber;

}