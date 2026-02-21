package com.proconsul.DTO;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderEvent {

    private Long orderId;
    private Long productId;
    private Integer quantity;
    private String status;
    private LocalDateTime createdAt;
}