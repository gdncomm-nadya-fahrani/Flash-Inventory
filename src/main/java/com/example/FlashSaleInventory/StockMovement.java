package com.example.FlashSaleInventory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockMovement {
    private Long id;
    private String productSku;
    private Long reservationId; // nullable when the movement isn't tied to a reservation

    @Enumerated(EnumType.STRING)
    private StockMovementType type;

    private int onHandChange;
    private int reservedChange;
    private String reason;
    private Instant createdAt;
}
