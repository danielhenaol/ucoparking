package co.edu.uco.notificationgateway.dto;

/**
 * DTO para respuestas de envío de email.
 */
public class EmailResponse {

    private boolean success;
    private String message;
    private String id;

    public EmailResponse(boolean success, String message, String id) {
        this.success = success;
        this.message = message;
        this.id = id;
    }

    public boolean isSuccess() { return success; }
    public String getMessage() { return message; }
    public String getId() { return id; }
}
