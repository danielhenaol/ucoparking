package co.edu.uco.ucoparking.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize -> authorize

                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        .requestMatchers(
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs/**"
                        ).permitAll()

                        .requestMatchers("/actuator/**").permitAll()
                        .requestMatchers("/api/v1/parking/events").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/parking/spaces/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/api/v1/parking/reservations").authenticated()
                        .requestMatchers(HttpMethod.PUT, "/api/v1/parking/reservations/**").authenticated()
                        .requestMatchers("/api/v1/students/**").permitAll()

                        .anyRequest().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()))
                .build();
    }
}