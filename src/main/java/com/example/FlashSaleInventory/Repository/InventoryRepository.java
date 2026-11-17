package com.example.FlashSaleInventory.Repository;

import java.util.Optional;

import com.example.FlashSaleInventory.Model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
    Optional<Inventory> findByProductSku(String productSku);
    boolean existsByProductSku(String productSku);
}
