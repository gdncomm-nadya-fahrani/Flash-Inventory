package com.example.FlashSaleInventory.Service.Model;

import com.example.FlashSaleInventory.Model.Inventory;
import com.example.FlashSaleInventory.Model.Product;
import com.example.FlashSaleInventory.Repository.InventoryRepository;
import com.example.FlashSaleInventory.Repository.ProductRepository;
import com.example.FlashSaleInventory.SaveInventoryRequest;
import com.example.FlashSaleInventory.Web.NotFoundException;
import com.example.FlashSaleInventory.WebModel.AvailabilityResponse;
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
    private final String PRODUCT_NOT_FOUND = "Product not found";
    private final String INVENTORY_NOT_FOUND = "Inventory not found";

    @Transactional
    public Inventory create(SaveInventoryRequest request) {
        productRepository.findBySku(request.getProductSku())
                .orElseThrow(() -> new NotFoundException(PRODUCT_NOT_FOUND));

        if (inventoryRepository.existsByProductSku(request.getProductSku())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Inventory already exists for this product"
            );
        }

        Inventory inventory = new Inventory();
        inventory.setProductSku(request.getProductSku());
        inventory.setOnHandQuantity(request.getOnHandQuantity());
        inventory.setReservedQuantity(0);

        return inventoryRepository.save(inventory);
    }

    @Transactional(readOnly = true)
    public AvailabilityResponse checkAvailability(
            String productSku,
            int quantity
    ) {
        Product product = productRepository.findBySku(productSku)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Product not found"
                ));

        Inventory inventory = inventoryRepository.findByProductSku(productSku)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Inventory not found"
                ));

        int availableQuantity =
                inventory.getOnHandQuantity() - inventory.getReservedQuantity();

        boolean canAdd =
                product.isActive() && availableQuantity >= quantity;

        return new AvailabilityResponse(
                productSku, availableQuantity, canAdd
        );
    }
}
