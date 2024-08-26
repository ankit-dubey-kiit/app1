package com.tracking.service.impl;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.tracking.dto.ApiResponse;
import com.tracking.exception.CustomBadRequestException;
import com.tracking.model.Customer;
import com.tracking.model.TrackingDetails;
import com.tracking.repo.CustomerRepository;
import com.tracking.repo.TrackingDetailsRepository;
import com.tracking.service.TrackingService;
import com.tracking.service.helper.TrackingServiceHelper;
import com.tracking.util.TrackingUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TrackingServiceImpl implements TrackingService{

	private final TrackingServiceHelper trackingServiceHelper;
	private final TrackingUtil trackingUtil;
	private final CustomerRepository customerRepo;
	private final TrackingDetailsRepository trackingDetailsRepository;
		
	@Async
    @Override
    public CompletableFuture<ApiResponse> createOrderTracking(Map<String, String> params) {
        return CompletableFuture.supplyAsync(() -> {
            String customerRef = params.get("customer_id");
            Optional<Customer> optionalCustomer = customerRepo.findByCustomerRef(customerRef);

            Customer customer = optionalCustomer.orElseThrow(() ->
                new CustomBadRequestException("Customer:" + customerRef + " is Invalid")
            );
            String trackingNumber = trackingUtil.generateTrackingNumber(params);

            TrackingDetails trackingDetails = trackingServiceHelper.prepareTrackingDetails(params, customer);
            trackingDetails.setTrackingNumber(trackingNumber);
            TrackingDetails trackingSaved = trackingDetailsRepository.save(trackingDetails);

            return trackingServiceHelper.prepareResponse(trackingSaved);
        });
    }
	
}
