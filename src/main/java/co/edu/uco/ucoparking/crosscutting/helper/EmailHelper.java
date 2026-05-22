package co.edu.uco.ucoparking.crosscutting.helper;

import java.util.regex.Pattern;

public final class EmailHelper {

    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private EmailHelper() {
    }

    public static boolean isValid(String email) {
        return email != null && EMAIL_PATTERN.matcher(email).matches();
    }
}
