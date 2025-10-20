package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.user.UserEarning;
import org.archivos.ecommercegt.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(ApplicationConfig.BASE_URL +"/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/top/spent")
    public ResponseEntity<List<UserEarning>> findUserByTopSpent(
            @RequestParam( required = false ) String startDate,
            @RequestParam( required = false ) String endDate
    ) {
        List<UserEarning> userTopSpents = userService.findUserByTopSpent(startDate, endDate);
        return ResponseEntity.ok(userTopSpents);
    }
}
