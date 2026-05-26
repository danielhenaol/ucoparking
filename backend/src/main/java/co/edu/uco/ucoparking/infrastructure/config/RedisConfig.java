package co.edu.uco.ucoparking.infrastructure.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de caché distribuida con Redis.
 * Redis almacena en memoria los datos más consultados
 * para reducir las llamadas a la base de datos.
 */
@Configuration
@EnableCaching
public class RedisConfig {
}