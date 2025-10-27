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
                        // Permite localhost, Netlify y cualquier subdominio de ngrok-free.dev
                        .allowedOriginPatterns(
                                "http://localhost:4200",
                                "https://manejoarchivosecommercegt.netlify.app",
                                "https://jade-flinty-dayton.ngrok-free.dev"
                        )
                        // Métodos HTTP permitidos
                        .allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS")
                        // Permite cualquier encabezado, incluyendo Authorization
                        .allowedHeaders("Authorization", "Content-Type", "Accept")
                        // Permite enviar cookies y credenciales
                        .allowCredentials(true)
                        // Exponer encabezados importantes si es necesario (opcional)
                        .exposedHeaders("Authorization", "Content-Disposition");
            }
        };
    }

}
