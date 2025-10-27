package org.archivos.ecommercegt.config;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Application config.
 */
@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

    /**
     * The constant BASE_URL.
     */
    public static final String BASE_URL = "/api/v1";
    /**
     * The constant ADMIN_EMAIL.
     */
    public static final String ADMIN_EMAIL = "admin@mail.com";
    /**
     * The constant APP_EMAIL.
     */
    public static final String APP_EMAIL = "brigidoavarado@gmail.com";

    private final UserRepository userRepository;

    /**
     * User details service user details service.
     *
     * @return the user details service
     */
    @Bean
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.CONFLICT,
                        "No se encontro un usuario con el correo: "+username));
    }

    /**
     * Password encoder password encoder.
     *
     * @return the password encoder
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Authentication manager authentication manager.
     *
     * @param config the config
     * @return the authentication manager
     * @throws Exception the exception
     */
    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config
    ) throws Exception{
        return config.getAuthenticationManager();
    }
}
