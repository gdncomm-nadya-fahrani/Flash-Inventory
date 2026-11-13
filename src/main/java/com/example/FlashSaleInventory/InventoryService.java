package com.example.FlashSaleInventory;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final ProductRepository productRepository;
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public AvailabilityResponse checkAvailability(String productSku, int quantity) {
        ProductDraft product = productRepository.findBySku(productSku).orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"));

        InventoryDraft inventory = inventoryRepository.findByProductSku(productSku).orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Inventory not found"));

        int availableQuantity = inventory.getOnHandQuantity() - inventory.getReservedQuantity();

        boolean canAdd = product.isActive() && availableQuantity >= quantity;

        return new AvailabilityResponse(productSku, availableQuantity, canAdd);
    }
}
