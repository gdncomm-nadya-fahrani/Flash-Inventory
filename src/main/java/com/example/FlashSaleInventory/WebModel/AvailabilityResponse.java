package com.example.FlashSaleInventory.WebModel;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AvailabilityResponse {
    private String productSku;
    private int availableQuantity;
    private boolean canAdd;
}
