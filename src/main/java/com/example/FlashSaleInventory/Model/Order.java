package com.example.FlashSaleInventory.Model;

import com.example.FlashSaleInventory.Enums.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Order {
    private Long id;
    private Long reservationId;
    private String customerId;
    private OrderStatus status;
    private BigDecimal totalAmount;
    private Instant createdAt;
}
