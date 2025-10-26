package org.archivos.ecommercegt.dto.notification;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class NotificationResponse {

    private final String subject;
    private final String content;

}
