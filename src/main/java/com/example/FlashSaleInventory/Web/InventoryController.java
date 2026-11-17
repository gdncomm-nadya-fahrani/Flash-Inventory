package com.example.FlashSaleInventory.Web;

import com.example.FlashSaleInventory.Model.Inventory;
import com.example.FlashSaleInventory.Repository.InventoryRepository;
import com.example.FlashSaleInventory.SaveInventoryRequest;
import com.example.FlashSaleInventory.Service.Model.InventoryService;
import com.example.FlashSaleInventory.WebModel.AvailabilityResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;
    private final InventoryRepository inventoryRepository;
    private final String INVENTORY_NOT_FOUND = "Inventory not found";

    @PostMapping
    public ResponseEntity<Inventory> create(
            @Valid @RequestBody SaveInventoryRequest request
    ) {
        Inventory saved = inventoryService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping("/{productSku}")
    public Inventory getByProductSku(@PathVariable String productSku) {
        return inventoryRepository.findByProductSku(productSku)
                .orElseThrow(() -> new NotFoundException(INVENTORY_NOT_FOUND));
    }

    @GetMapping("/availability")
    public AvailabilityResponse checkAvailability(
            @RequestParam @NotBlank String productSku,
            @RequestParam @Positive int quantity
    ) {
        return inventoryService.checkAvailability(productSku, quantity);
    }
}