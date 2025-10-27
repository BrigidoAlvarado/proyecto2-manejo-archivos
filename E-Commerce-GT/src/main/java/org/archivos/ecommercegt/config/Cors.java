package org.archivos.ecommercegt.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuración CORS para Spring Boot compatible con Angular + ngrok.
 */
@Configuration
public class Cors {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOriginPatterns(
                                "http://localhost:4200",
                                "https://manejoarchivosecommercegt.netlify.app",
                                "https://jade-flinty-dayton.ngrok-free.dev"
                        )
                        // Métodos HTTP permitidos
                        .allowedMethods("OPTIONS", "GET", "POST", "PUT", "PATCH", "DELETE")
                        // Permite cualquier encabezado, incluyendo Authorization
                        .allowedHeaders("*")
                        // Permite enviar cookies y credenciales
                        .allowCredentials(true)
                        // Exponer encabezados importantes si es necesario (opcional)
                        .exposedHeaders("Authorization", "Content-Disposition")
                        ;
            }
        };
    }

}
