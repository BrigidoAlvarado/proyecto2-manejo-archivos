package org.archivos.ecommercegt.controllers;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.notification.NotificationResponse;
import org.archivos.ecommercegt.services.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * The type Notification controller.
 */
@RestController
@RequestMapping(ApplicationConfig.BASE_URL + "/notification")
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    /**
     * Get all notifications by user response entity.
     *
     * @param userId the user id
     * @return the response entity
     */
    @GetMapping("/{userId}")
    public ResponseEntity<List<NotificationResponse>> getAllNotificationsByUser(
            @PathVariable int userId
    ){
        List<NotificationResponse> responses = notificationService.getNotificationsByUserId(userId);
        return ResponseEntity.ok(responses);
    }
}
