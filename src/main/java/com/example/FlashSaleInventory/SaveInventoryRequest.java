package com.example.FlashSaleInventory;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SaveInventoryRequest {
    @NotBlank
    private String productSku;

    @NotNull
    @PositiveOrZero
    private Integer onHandQuantity;
}
