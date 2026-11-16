package com.example.FlashSaleInventory;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductDraft, Long> {
    Optional<ProductDraft> findBySku(String sku);
    boolean existsBySku(String sku);
}
