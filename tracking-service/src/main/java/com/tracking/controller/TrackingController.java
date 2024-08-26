package com.tracking.controller;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tracking.dto.ApiResponse;
import com.tracking.service.TrackingService;
import com.tracking.validator.OrderValidator;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping(value = "/tracking")
public class TrackingController {

	private final TrackingService trackingService;
	private final OrderValidator validator;
	
	@GetMapping("/next-tracking-number")
    public CompletableFuture<ApiResponse> createOrderTracking(@RequestParam Map<String, String> params) {
        validator.validateOrder(params);
        return trackingService.createOrderTracking(params);
    }
}
