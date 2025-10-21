package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.dto.user.UserEarning;
import org.archivos.ecommercegt.dto.user.UserPackagesOrdered;
import org.archivos.ecommercegt.dto.user.UserProductsSend;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.UserRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public User getUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository
                .findByEmail(authentication.getName())
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

}
