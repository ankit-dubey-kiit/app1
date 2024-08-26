package com.tracking.service;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import com.tracking.dto.ApiResponse;

public interface TrackingService {

	CompletableFuture<ApiResponse> createOrderTracking(Map<String, String> params);
}
