package com.tracking.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import com.tracking.exception.CustomBadRequestException;
import com.tracking.service.TrackingCacheService;

@Service
public class TrackingCacheServiceImpl implements TrackingCacheService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    private static final String TRACKING_NUMBER_PREFIX = "tracking_number:";

    @Override
    public boolean isTrackingNumberExists(String trackingNumber) {
    	try {
    		return redisTemplate.hasKey(TRACKING_NUMBER_PREFIX + trackingNumber);
    	}
    	catch(Exception e) {
    		throw new CustomBadRequestException("Failed to get key from redis cache");
    	}
        
    }

    @Override
    public void saveTrackingNumber(String trackingNumber) {
        try{
        	redisTemplate.opsForValue().set(TRACKING_NUMBER_PREFIX + trackingNumber, "exists", 1, TimeUnit.DAYS);
        }
        catch(Exception e) {
    		throw new CustomBadRequestException("Failed to set key to redis cache");
    	}
    }
}
