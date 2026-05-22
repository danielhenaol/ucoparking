package co.edu.uco.ucoparking.crosscutting.helper;

import java.util.UUID;

/**
 * Utilidades para identificadores UUID.
 */
public final class UUIDHelper {

    private UUIDHelper() {
    }

    public static UUID generate() {
        return UUID.randomUUID();
    }
}
