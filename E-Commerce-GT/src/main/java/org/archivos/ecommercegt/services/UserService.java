package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.user.*;
import org.archivos.ecommercegt.models.Roletype;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe")
                );
    }

    public User getUserByEmail(String email) {
        return userRepository
                .findByEmail(email)
                .orElseThrow(
                        () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe")
                );
    }

    public List<UserEarning> findUserByTopSpent(String startDate, String endDate ) {

        try{
            Instant startInstant = null;
            Instant endInstant = null;

            if( startDate != null ) startInstant = LocalDate.parse( startDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();
            if (endDate != null ) endInstant = LocalDate.parse( endDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();

            final Pageable pageable = PageRequest.of(0, 5);
            return userRepository.findUserByTopSpent(startInstant, endInstant, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<UserProductsSend> finUserByProductsSend(String startDate, String endDate) {
            try{
                Instant startInstant = null;
                Instant endInstant = null;

                if( startDate != null ) startInstant = LocalDate.parse( startDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();
                if (endDate != null ) endInstant = LocalDate.parse( endDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();

                final Pageable pageable = PageRequest.of(0, 5);
                return userRepository.findUserByProductsSend(startInstant, endInstant, pageable);
            }catch (Exception e){
                e.printStackTrace();
                throw e;
            }
    }

    public List<UserPackagesOrdered> finUserByPackagesOrdered(String startDate, String endDate) {
        try{
            Instant startInstant = null;
            Instant endInstant = null;

            if( startDate != null ) startInstant = LocalDate.parse( startDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();
            if (endDate != null ) endInstant = LocalDate.parse( endDate ).atStartOfDay(ZoneId.systemDefault() ).toInstant();

            final Pageable pageable = PageRequest.of(0, 10);
            return userRepository.findUserByPackagesOrdered(startInstant, endInstant, pageable);
        }catch (Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    public List<UserProductsApprove> finUserByProductsApprove( ) {
        final Pageable pageable = PageRequest.of(0, 10);
        return userRepository.findUsersByAProvedProducts( pageable );
    }

    public List<UserResponse> getAllUsers(){
        final List<User> users = userRepository.findAll();
        final List<UserResponse> list = new ArrayList<>();

        for (User user : users) {
            list.add(
                    UserResponse.builder()
                            .id( user.getId() )
                            .role( user.getRole().getName() )
                            .enabled( user.isEnabled() )
                            .username( user.getUsername() )
                            .email( user.getEmail() ).build()
            );
        }
        return list;
    }

    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no existe")
        );
    }

    public UserResponse getUserResponseById(int id) {
        User user = getUserById(id);
        return UserResponse.builder()
                .id(user.getId())
                .username( user.getName() )
                .email(user.getEmail())
                .role(user.getRole().getName())
                .build();
    }

    public void updateUser(UpdateUserRequest request) {
        // Get user
        User user = getUserById(request.getId());
        // Set values
        user.setName(request.getName());
        // Validate if is principal admin
        if ( user.getEmail().equals(ApplicationConfig.ADMIN_EMAIL) ){
            if (!request.getEmail().equals(ApplicationConfig.ADMIN_EMAIL)){
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El email del admin principal no se puede actualizar");
            }
        }
        user.setEmail(request.getEmail());
        user.setRole( new Roletype(request.getRole()));

        if (request.getPassword() != null){
            user.setPassword( passwordEncoder.encode(request.getPassword()) );
        }
        userRepository.save(user);
    }
}
