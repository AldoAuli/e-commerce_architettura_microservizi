package com.proconsul.controller;

import com.proconsul.DTO.CreateOrderRequest;
import com.proconsul.entity.Order;
import com.proconsul.service.OrderService;
import lombok.RequiredArgsConstructor;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rest/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @CircuitBreaker(name = "productService", fallbackMethod = "fallbackCreateOrder")
    public Order createOrder(@RequestBody CreateOrderRequest request) {
        return orderService.createOrder(
                request.getProductId(),
                request.getQuantity()
        );
    }

    public Order fallbackCreateOrder(CreateOrderRequest request, Throwable throwable) {
        throw new RuntimeException("Servizio prodotti non disponibile: " + throwable.getMessage());
    }
}
