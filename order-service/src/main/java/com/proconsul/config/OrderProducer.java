package com.proconsul.config;

import com.proconsul.DTO.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    private static final String TOPIC = "order.events";

    public void sendOrderCreatedEvent(Long orderId,Long productId,Integer quantity) {

        OrderEvent event = new OrderEvent(orderId,"CREATED",productId,quantity);

        kafkaTemplate.send(TOPIC, event);

        System.out.println("Order event sent for orderId: " + orderId);
    }
}