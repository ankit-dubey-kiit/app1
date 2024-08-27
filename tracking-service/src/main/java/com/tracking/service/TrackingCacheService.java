package com.tracking.service;

public interface TrackingCacheService {

	boolean isTrackingNumberExists(String trackingNumber);
    void saveTrackingNumber(String trackingNumber);
}
