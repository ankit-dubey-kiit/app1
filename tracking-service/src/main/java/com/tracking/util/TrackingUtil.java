package com.tracking.util;

import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.binary.Base32;
import org.springframework.stereotype.Component;

@Component
public class TrackingUtil {

	private static final int MAX_LENGTH = 16;

	public String generateTrackingNumber(Map<String, String> params) {
		Base32 base32 = new Base32();
        UUID uuid = UUID.randomUUID();
        byte[] uuidBytes = toBytes(uuid);
 
        String base32Encoded = base32.encodeAsString(uuidBytes).replaceAll("=", "");
        return base32Encoded.length() > MAX_LENGTH ? base32Encoded.substring(0, MAX_LENGTH) : base32Encoded;
	}
	
	private static byte[] toBytes(UUID uuid) {
        byte[] bytes = new byte[16];
        long mostSigBits = uuid.getMostSignificantBits();
        long leastSigBits = uuid.getLeastSignificantBits();
        for (int i = 0; i < 8; i++) {
            bytes[i] = (byte) (mostSigBits >>> (8 * (7 - i)));
            bytes[8 + i] = (byte) (leastSigBits >>> (8 * (7 - i)));
        }
        return bytes;
    }
}
