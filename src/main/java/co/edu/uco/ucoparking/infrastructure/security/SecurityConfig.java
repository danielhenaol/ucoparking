package co.edu.uco.ucoparking.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuración de seguridad para proteger la API con Auth0.
 *
 * El frontend React inicia sesión con Auth0, recibe un token JWT
 * y lo envía al backend en el encabezado Authorization: Bearer TOKEN.
 *
 * Spring Security valida ese token contra Auth0.
 */
@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        // Permite las peticiones preflight de CORS.
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // Swagger queda libre para poder documentar y probar.
                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        // Actuator queda libre para monitoreo básico.
                        .requestMatchers("/actuator/**").permitAll()

                        // SSE queda libre porque EventSource no envía fácilmente Authorization header.
                        .requestMatchers("/api/v1/parking/events").permitAll()

                        // Endpoints de parqueadero protegidos con Auth0.
                        .requestMatchers(HttpMethod.GET, "/api/v1/parking/spaces/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/parking/reservations").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/parking/reservations/**").authenticated()

                        // Endpoint de estudiantes queda libre por ahora.
                        .requestMatchers("/api/v1/students/**").permitAll()

                        // Lo demás queda permitido para no romper configuración inicial.
                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}