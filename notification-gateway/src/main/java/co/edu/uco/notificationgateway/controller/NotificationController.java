package co.edu.uco.notificationgateway.controller;

import co.edu.uco.notificationgateway.dto.EmailRequest;
import co.edu.uco.notificationgateway.dto.EmailResponse;
import co.edu.uco.notificationgateway.service.ResendEmailService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controlador REST del Notification Gateway de UCO Parking.
 * Recibe solicitudes de envío de notificaciones y las despacha
 * a través de Resend.
 */
@RestController
@RequestMapping("/api/v1/notifications")
@CrossOrigin(origins = "*")
public class NotificationController {

    private final ResendEmailService emailService;

    public NotificationController(ResendEmailService emailService) {
        this.emailService = emailService;
    }

    /**
     * Envía un email de notificación.
     * Usado para: reserva creada, reserva cancelada, expiración próxima, etc.
     */
    @PostMapping("/email")
    public ResponseEntity<EmailResponse> sendEmail(@RequestBody EmailRequest request) {
        if (request.getTo() == null || request.getTo().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new EmailResponse(false, "El destinatario es obligatorio.", null));
        }
        if (request.getSubject() == null || request.getSubject().isBlank()) {
            return ResponseEntity.badRequest()
                    .body(new EmailResponse(false, "El asunto es obligatorio.", null));
        }

        EmailResponse response = emailService.sendEmail(request);
        return response.isSuccess()
                ? ResponseEntity.ok(response)
                : ResponseEntity.internalServerError().body(response);
    }

    /**
     * Envía notificación de reserva creada.
     */
    @PostMapping("/reservation-created")
    public ResponseEntity<EmailResponse> notifyReservationCreated(@RequestBody EmailRequest request) {
        request.setSubject("UCO Parking - Reserva creada exitosamente");
        request.setBody("Tu reserva en UCO Parking fue creada exitosamente. " + request.getBody());
        return sendEmail(request);
    }

    /**
     * Envía notificación de reserva por expirar.
     */
    @PostMapping("/reservation-expiring")
    public ResponseEntity<EmailResponse> notifyReservationExpiring(@RequestBody EmailRequest request) {
        request.setSubject("UCO Parking - Tu reserva está por expirar");
        request.setBody("Tu reserva en UCO Parking está próxima a expirar. " + request.getBody());
        return sendEmail(request);
    }

    /**
     * Envía notificación de reserva cancelada.
     */
    @PostMapping("/reservation-cancelled")
    public ResponseEntity<EmailResponse> notifyReservationCancelled(@RequestBody EmailRequest request) {
        request.setSubject("UCO Parking - Reserva cancelada");
        request.setBody("Tu reserva en UCO Parking fue cancelada. " + request.getBody());
        return sendEmail(request);
    }
}
