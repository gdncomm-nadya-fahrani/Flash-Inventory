package com.example.FlashSaleInventory.repository;

import java.util.Optional;

import com.example.FlashSaleInventory.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);
}
