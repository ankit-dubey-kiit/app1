package com.tracking.validator;

import java.time.DateTimeException;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.tracking.exception.CustomBadRequestException;

@Component
public class OrderValidator {

    private static final int COUNTRY_CODE_LENGTH = 2;
    private static final int MAX_DECIMALS = 3;
    private static final DateTimeFormatter RFC_3339_FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    private static final Pattern SLUG_PATTERN = Pattern.compile("^[a-z0-9]+(-[a-z0-9]+)*$");



    public void validateOrder(Map<String, String> params) {
        validateField(params.get("origin_country_id"), "Origin Country Id", COUNTRY_CODE_LENGTH);
        validateField(params.get("destination_country_id"), "Destination Country Id", COUNTRY_CODE_LENGTH);
        validateField(params.get("weight"), "Order Weight", -1); 
        validateField(params.get("created_at"),"Created At",-1);
        validateField(params.get("customer_id"), "Customer Id", -1);
        validateField(params.get("customer_name"), "Customer name", -1);
        validateCustomerSlug(params.get("customer_slug"), params.get("customer_name"));
        
        validateWeight(params.get("weight"));
    }

    private void validateField(String fieldValue, String fieldName, int expectedLength) {
        if (fieldValue == null || fieldValue.trim().isEmpty()) {
            throw new CustomBadRequestException(fieldName + " is invalid or missing");
        }
        if (expectedLength > 0 && fieldValue.length() != expectedLength) {
            throw new CustomBadRequestException(fieldName + " must be exactly " + expectedLength + " characters long");
        }
    }

    private void validateWeight(String weight) {
        if (weight == null || weight.trim().isEmpty()) {
            throw new CustomBadRequestException("Order Weight is invalid or missing");
        }

        String[] weightDecimals = weight.split("\\.");
        if (weightDecimals.length > 1 && weightDecimals[1].length() > MAX_DECIMALS) {
            throw new CustomBadRequestException("Weight Decimal value should not be greater than " + MAX_DECIMALS);
        }
    }
    
    private void validateCreatedAt(String createdAt) {
        if (createdAt == null || createdAt.trim().isEmpty()) {
            throw new CustomBadRequestException("Create Time is invalid or missing");
        }

        try {
            OffsetDateTime.parse(createdAt, RFC_3339_FORMATTER);
        } catch (DateTimeException e) {
            throw new CustomBadRequestException("Create Time must be in RFC 3339 format");
        }
    }
    
    private void validateCustomerSlug(String customerSlug, String customerName) {
        if (customerSlug == null || customerSlug.trim().isEmpty()) {
            throw new CustomBadRequestException("Customer slug is invalid or missing");
        }

        String expectedSlug = generateSlug(customerName);
        if (!customerSlug.equals(expectedSlug)) {
            throw new CustomBadRequestException("Customer slug is missing on invalid");
        }

        if (!SLUG_PATTERN.matcher(customerSlug).matches()) {
            throw new CustomBadRequestException("Customer slug is missing on invalid");
        }
    }

    private String generateSlug(String name) {
        if (name == null) {
            return "";
        }
        return name.toLowerCase()
                .replaceAll("[^a-z0-9]+", "-")
                .replaceAll("^-|-$", ""); 
    }

}
