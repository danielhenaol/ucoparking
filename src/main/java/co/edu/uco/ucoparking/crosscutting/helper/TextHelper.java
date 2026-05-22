package co.edu.uco.ucoparking.crosscutting.helper;

/**
 * Utilidades para trabajar con textos.
 */
public final class TextHelper {

    private TextHelper() {
    }

    public static boolean isNullOrBlank(String value) {
        return value == null || value.trim().isEmpty();
    }

    public static String trim(String value) {
        return value == null ? "" : value.trim();
    }
}
