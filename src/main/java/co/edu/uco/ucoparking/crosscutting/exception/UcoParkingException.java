package co.edu.uco.ucoparking.crosscutting.exception;

public class UcoParkingException extends RuntimeException {

    public UcoParkingException(String message) {
        super(message);
    }

    public UcoParkingException(String message, Throwable cause) {
        super(message, cause);
    }
}
