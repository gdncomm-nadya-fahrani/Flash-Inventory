package com.example.FlashSaleInventory;

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
public class Product {
    private Long id;
    private String sku;
    private String name;
    private BigDecimal price;
    private boolean active;
    private Instant createdAt;
    private Instant updatedAt;

}
