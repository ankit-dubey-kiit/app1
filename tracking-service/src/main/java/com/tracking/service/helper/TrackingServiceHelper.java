package com.tracking.service.helper;

import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Date;

import org.springframework.stereotype.Component;

import com.tracking.dto.ApiResponse;
import com.tracking.dto.TrackingNumberResponse;
import com.tracking.model.Customer;
import com.tracking.model.TrackingDetails;

@Component
public class TrackingServiceHelper {

	private static final DateTimeFormatter RFC_3339_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
	private static final String SUCCESS_STATUS = "SUCCESS"; 
	private static final String DUMMY_USER = "ankit.dubey";


	public TrackingDetails prepareTrackingDetails(Map<String, String> params, Customer customer) {
		TrackingDetails trackingDetails = new TrackingDetails();
        
        String originCountryCode = params.get("origin_country_id");
        String destinationCountryCode = params.get("destination_country_id");
        String weightStr = params.get("weight");
        String createTimeStr = params.get("created_at");

        try {
            Double weight = Double.parseDouble(weightStr);
            //OffsetDateTime createTime = OffsetDateTime.parse(createTimeStr, RFC_3339_FORMATTER);
            //Date createTimeDate = Date.from(createTime.toInstant());

            trackingDetails.setOriginCountryId(originCountryCode);
            trackingDetails.setDestinationCountryId(destinationCountryCode);
            trackingDetails.setWeight(weight);
            trackingDetails.setCustomer(customer);
            trackingDetails.setCreateUser(DUMMY_USER);
            trackingDetails.setCreateTime(new Date());
            trackingDetails.setUpdateUser(DUMMY_USER);
            trackingDetails.setUpdateTime(new Date());
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to create tracking number for the order:: " + params, e);
        } 

        return trackingDetails;

    }

	
	public ApiResponse prepareResponse(TrackingDetails trackingSaved) {
		ApiResponse reponse = new ApiResponse();
		TrackingNumberResponse trackingResponse  = new TrackingNumberResponse();
		
		trackingResponse.setCreatedAt(trackingSaved.getCreateTime());
		trackingResponse.setTrackingNumber(trackingSaved.getTrackingNumber());
		
		reponse.setStatus(SUCCESS_STATUS);
		reponse.setResponse(trackingResponse);
		return reponse;
	}
	
}
