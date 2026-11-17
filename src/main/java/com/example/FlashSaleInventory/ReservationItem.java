package com.example.FlashSaleInventory;


import com.example.FlashSaleInventory.Enums.ReservationStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class ReservationItem {
    private Long id;
    private Long customerId;
    private ReservationStatus status;
    private Instant expiresAt;
    private Instant createdAt;
    private Instant updatedAt;

}
