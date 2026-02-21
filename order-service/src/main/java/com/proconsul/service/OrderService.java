package com.proconsul.service;

import com.proconsul.entity.Order;

public interface OrderService {

    public Order createOrder(Long productId, Integer quantity);
}
