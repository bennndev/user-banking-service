package com.lab10.user_banking_service.adapter.out.persistence;

import org.springframework.stereotype.Component;
import com.lab10.user_banking_service.application.port.out.NotificationPort;

@Component
public class ConsoleNotificationAdapter implements NotificationPort {
    @Override
    public void sendNotification(String message) {
        System.out.println("Notificación: " + message);
    }
}
