package org.archivos.ecommercegt.auth;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.JwtService;
import org.archivos.ecommercegt.models.Roletype;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.models.enums.Role;
import org.archivos.ecommercegt.repository.ShoppingCartRepository;
import org.archivos.ecommercegt.repository.UserRepository;
import org.archivos.ecommercegt.services.CommonUserService;
import org.archivos.ecommercegt.services.SanctionService;
import org.archivos.ecommercegt.services.ShoppingCartService;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

/**
 * The type Authenticator service.
 */
@Service
@RequiredArgsConstructor
public class AuthenticatorService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CommonUserService commonUserService;
    private final SanctionService sanctionService;

    /**
     * Register authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
    @Transactional
    public AuthenticationResponse register(RegisterRequest request) {

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "El correo ya se encuentra registrado"
            );
        }

        var user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Roletype.builder()
                        .name(request.getRole())
                        .build())
                .enabled(true)
                .build();

        userRepository.save(user);

        if (user.getRole().getName().equals(Role.COMMON.name())) commonUserService.initUser(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    /**
     * Authenticate authentication response.
     *
     * @param request the request
     * @return the authentication response
     */
    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (AuthenticationException e) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED,
                    "Correo Electronico o contraseña invalidos"
            );
        }

        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.UNAUTHORIZED,
                                "Correo Electronico o contraseña invalidos"
                        )
                );

        sanctionService.validateSanction(user);

        var jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}