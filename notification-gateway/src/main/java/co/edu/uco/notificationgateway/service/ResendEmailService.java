package co.edu.uco.notificationgateway.service;

import co.edu.uco.notificationgateway.dto.EmailRequest;
import co.edu.uco.notificationgateway.dto.EmailResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * Servicio que envía emails usando la API de Resend.
 * Resend es un servicio adoptado de envío de emails con 3000 emails/mes gratis.
 */
@Service
public class ResendEmailService {

    private static final String RESEND_API_URL = "https://api.resend.com/emails";

    @Value("${resend.api.key}")
    private String apiKey;

    @Value("${resend.from.email}")
    private String fromEmail;

    private final RestTemplate restTemplate = new RestTemplate();

    public EmailResponse sendEmail(EmailRequest request) {
        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(apiKey);

            Map<String, Object> body = Map.of(
                    "from", fromEmail,
                    "to", new String[]{request.getTo()},
                    "subject", request.getSubject(),
                    "html", "<p>" + request.getBody() + "</p>"
            );

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(body, headers);
            ResponseEntity<Map> response = restTemplate.postForEntity(RESEND_API_URL, entity, Map.class);

            String id = response.getBody() != null ? String.valueOf(response.getBody().get("id")) : "unknown";
            return new EmailResponse(true, "Email enviado exitosamente.", id);

        } catch (Exception e) {
            return new EmailResponse(false, "Error al enviar el email: " + e.getMessage(), null);
        }
    }
}
