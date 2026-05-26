package co.edu.uco.configservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * UCO Parking - Config Server
 * Centraliza los parámetros de configuración del sistema.
 * Los parámetros se leen desde la carpeta config-repo.
 */
@SpringBootApplication
@EnableConfigServer
public class ConfigServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConfigServiceApplication.class, args);
    }
}
