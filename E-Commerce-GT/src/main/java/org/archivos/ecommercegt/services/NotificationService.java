package org.archivos.ecommercegt.services;

import lombok.RequiredArgsConstructor;
import org.archivos.ecommercegt.config.ApplicationConfig;
import org.archivos.ecommercegt.dto.notification.NotificationResponse;
import org.archivos.ecommercegt.models.DeliveryPackage;
import org.archivos.ecommercegt.models.Notification;
import org.archivos.ecommercegt.models.Product;
import org.archivos.ecommercegt.models.User;
import org.archivos.ecommercegt.repository.NotificationRepository;
import org.archivos.ecommercegt.services.utilities.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;
    @Autowired
    private MailService mailService;
    @Autowired
    private UserService userService;

    public void notifyPackageStatusChange( DeliveryPackage deliveryPackage ) {
        // crear el mensaje
        final String subject = "E Commerce GT pedido No." + deliveryPackage.getId();
        final String text = "El pedido No." + deliveryPackage.getId() + " ha sido envido"
                + " fecha estimada de entrega: " + deliveryPackage.getDeliveryDate().toString();
        notify(deliveryPackage.getShoppingCart().getUser(), subject, text);
    }

    public void notifyNoApproveProduct( Product product ) {
        final String subject = "E Commerce GT producto No." + product.getId();
        final String text = "El producto No." + product.getId()
                + " con nombre " + product.getName() + " ha sido rechazado por nuestro equipo.";

        notify( product.getUser(), subject, text);
    }

    public void notifyApproveProduct( Product product ) {
        final String subject = "E Commerce GT producto No." + product.getId();
        final String text = "El producto No." + product.getId()
                + " con nombre " + product.getName() + " ha sido aprovado por nuestro equipo.";

        notify( product.getUser(), subject, text);
    }

    public void notify(User user, String subject, String text) {

        // guardar la notificacion en la base de datos
        Notification notification = new Notification();
        notification.setSubject( subject );
        notification.setContent( text );
        notification.setUser( user );
        notification.setSender( ApplicationConfig.APP_EMAIL );
        notificationRepository.save( notification );

        // Enviar el correo
        mailService.sendMail(
                user.getEmail(),
                subject,
                text
        );
    }

    public List<NotificationResponse> getNotificationsByUserId(int userId) {
        final User user = userService.getUserById(userId);
        final List<Notification> notifications = notificationRepository.findByUser(user);
        final List<NotificationResponse> responses = new ArrayList<>();

        for (Notification notification : notifications) {
            responses.add(
              NotificationResponse.builder()
                      .content( notification.getContent() )
                      .subject( notification.getSubject() )
                      .build()
            );
        }
        return responses;
    }


}
