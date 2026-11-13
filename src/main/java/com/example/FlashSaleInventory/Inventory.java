package com.example.FlashSaleInventory;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Inventory {
    private Long id;
    private String productSku;
    private int onHandQuantity;
    private int reservedQuantity;
    private long version;
    private Instant updatedAt;
}
