package co.edu.uco.ucoparking.infrastructure.observability;

import java.io.IOException;
import java.security.Principal;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class MdcLoggingFilter extends OncePerRequestFilter {

    private static final String REQUEST_ID = "requestId";
    private static final String HTTP_METHOD = "httpMethod";
    private static final String REQUEST_URI = "requestUri";
    private static final String USER = "user";

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        String requestId = getOrCreateRequestId(request);
        String user = getAuthenticatedUser(request);

        try {
            MDC.put(REQUEST_ID, requestId);
            MDC.put(HTTP_METHOD, request.getMethod());
            MDC.put(REQUEST_URI, request.getRequestURI());
            MDC.put(USER, user);

            response.setHeader("X-Request-Id", requestId);

            filterChain.doFilter(request, response);
        } finally {
            MDC.remove(REQUEST_ID);
            MDC.remove(HTTP_METHOD);
            MDC.remove(REQUEST_URI);
            MDC.remove(USER);
        }
    }

    private String getOrCreateRequestId(HttpServletRequest request) {
        String requestId = request.getHeader("X-Request-Id");

        if (requestId == null || requestId.trim().isEmpty()) {
            return UUID.randomUUID().toString();
        }

        return requestId.trim();
    }

    private String getAuthenticatedUser(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();

        if (principal == null || principal.getName() == null || principal.getName().trim().isEmpty()) {
            return "anonymous";
        }

        return principal.getName().trim();
    }
}