package com.example.FlashSaleInventory.Repository;

import java.util.Optional;

import com.example.FlashSaleInventory.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findBySku(String sku);
    boolean existsBySku(String sku);
}
