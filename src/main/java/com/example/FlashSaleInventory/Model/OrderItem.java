package com.example.FlashSaleInventory.Model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class OrderItem {
    private Long id;
    private Long orderId;
    private String productSku;
    private int quantity;
    private BigDecimal unitPrice;
}
