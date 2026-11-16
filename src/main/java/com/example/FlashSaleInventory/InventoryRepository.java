package com.example.FlashSaleInventory;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<InventoryDraft, Long> {
    Optional<InventoryDraft> findByProductSku(String productSku);
}
