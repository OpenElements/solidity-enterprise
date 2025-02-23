package com.openelements.hiero.smartcontract.abi.util;

import java.nio.charset.StandardCharsets;
import java.util.Objects;
import org.jspecify.annotations.NonNull;

public class HexConverter {

    private static final byte[] HEX_ARRAY = "0123456789ABCDEF".getBytes(StandardCharsets.US_ASCII);

    @NonNull
    public static String bytesToHex(@NonNull final byte[] bytes) {
        //see https://stackoverflow.com/questions/9655181/java-convert-a-byte-array-to-a-hex-string
        Objects.requireNonNull(bytes, "bytes must not be null");
        final byte[] hexChars = new byte[bytes.length * 2];
        for (int j = 0; j < bytes.length; j++) {
            int v = bytes[j] & 0xFF;
            hexChars[j * 2] = HEX_ARRAY[v >>> 4];
            hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
        }
        return new String(hexChars, StandardCharsets.UTF_8).toLowerCase();
    }

    @NonNull
    public static byte[] hexToBytes(@NonNull final String hex) {
        Objects.requireNonNull(hex, "hex must not be null");
        int len = hex.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(hex.charAt(i), 16) << 4)
                    + Character.digit(hex.charAt(i+1), 16));
        }
        return data;
    }
}
