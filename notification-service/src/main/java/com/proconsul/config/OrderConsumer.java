package com.proconsul.config;

import com.proconsul.entity.NotificationLog;
import com.proconsul.service.NotificationService;
import lombok.AllArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order.events", groupId = "notification-group")
    public void consumeOrderEvent(String message) {
        System.out.println("✅ Notification received: " + message);

        // parsing semplice: "Order created: 1"
        String[] parts = message.split(":");
        Long orderId = Long.parseLong(parts[1].trim());

        NotificationLog notification = new NotificationLog();
        notification.setOrderId(orderId);
        notification.setStatus("RECEIVED");
        notification.setCreatedAt(LocalDateTime.now());
        notificationService.saveNotification(notification);
    }
}
