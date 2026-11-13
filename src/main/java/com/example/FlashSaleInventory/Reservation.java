package com.example.FlashSaleInventory;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class Reservation {
    private Long id;
    private Long reservationId;
    private String productSku;
    private int quantity;
    private BigDecimal unitPriceAtReservation;
}
