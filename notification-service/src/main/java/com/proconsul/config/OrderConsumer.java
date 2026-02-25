package com.proconsul.config;

import com.proconsul.DTO.OrderEvent;
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
    public void consumeOrderEvent(OrderEvent event) {

        System.out.println("Notification received for order: " + event.getOrderId());

        NotificationLog notification = new NotificationLog();
        notification.setOrderId(event.getOrderId());
        notification.setStatus("RECEIVED");
        notification.setProductId(event.getProductId());
        notification.setQuantity(event.getQuantity());
        notification.setCreatedAt(LocalDateTime.now());

        notificationService.saveNotification(notification);
    }
}
