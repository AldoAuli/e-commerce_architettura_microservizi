package com.proconsul.service;

import com.proconsul.DTO.OrderEvent;
import com.proconsul.DTO.Product;
import com.proconsul.config.OrderProducer;
import com.proconsul.entity.Order;
import com.proconsul.repository.OrderRepository;
import lombok.AllArgsConstructor;
import org.apache.kafka.common.errors.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final RestTemplate restTemplate;
    private final OrderProducer producer;

    public Order createOrder(Long productId, Integer quantity) {

        String baseUrl = "http://product-service/rest/api/products/";

        ResponseEntity<Product> response =
                restTemplate.getForEntity(baseUrl + productId, Product.class);
        Product product = response.getBody();

        if (product.getQuantity() < quantity) {
            throw new RuntimeException("Not enough stock");
        }

        restTemplate.put(
                baseUrl + productId + "/decrease?quantity=" + quantity,
                null
        );

        Order order = new Order(null, productId, quantity, "CREATED");
        Order saved = repository.save(order);
        producer.sendOrderCreatedEvent(saved.getId(), productId, quantity);

        return saved;
    }
}