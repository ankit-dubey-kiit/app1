package com.tracking.util;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.time.Instant;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Component;

import com.tracking.exception.CustomBadRequestException;

@Component
public class TrackingUtil {

	private static final String BASE32_CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	private static final int MAX_LENGTH = 16;

	private static final ConcurrentMap<String, Boolean> issuedTrackingNumbers = new ConcurrentHashMap<>();

	public String generateTrackingNumber(Map<String, String> params) {
		String sourceCountry = params.get("origin_country_id");
		String destinationCountry = params.get("destination_country_id");
		String timestamp = String.valueOf(Instant.now().toEpochMilli());
		String uuid = UUID.randomUUID().toString();

		String combinedString = String.join("|", sourceCountry, destinationCountry, timestamp, uuid);

		String trackingNumber;
		do {
			try {
				MessageDigest digest = MessageDigest.getInstance("SHA-256");
				byte[] hashBytes = digest.digest(combinedString.getBytes(StandardCharsets.UTF_8));
				String base32String = bytesToBase32(hashBytes);

				trackingNumber = base32String.length() <= MAX_LENGTH ? base32String
						: base32String.substring(0, MAX_LENGTH);
				if (!issuedTrackingNumbers.containsKey(trackingNumber)) {
					issuedTrackingNumbers.put(trackingNumber, true);
					return trackingNumber;
				}
				combinedString = String.join("|", sourceCountry, destinationCountry, timestamp,
						UUID.randomUUID().toString());

			} catch (Exception e) {
				throw new CustomBadRequestException("Failed to generate Tracking Number");
			}
		} while (true);
	}

	private static String bytesToBase32(byte[] bytes) {
		StringBuilder sb = new StringBuilder();
		java.math.BigInteger bigInt = new java.math.BigInteger(1, bytes);

		try {
			while (bigInt.compareTo(java.math.BigInteger.ZERO) > 0) {
				java.math.BigInteger[] divmod = bigInt.divideAndRemainder(java.math.BigInteger.valueOf(32));
				bigInt = divmod[0];
				sb.append(BASE32_CHARACTERS.charAt(divmod[1].intValue()));
			}
			while (sb.length() % 8 != 0) {
				sb.append('0');
			}
			return sb.reverse().toString();
		} catch (Exception e) {
			throw new CustomBadRequestException("Failed to generate Tracking Number");
		}
	}
}
