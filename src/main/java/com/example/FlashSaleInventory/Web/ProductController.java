package com.example.FlashSaleInventory.Web;

import com.example.FlashSaleInventory.Model.Product;
import com.example.FlashSaleInventory.SaveProductRequest;
import com.example.FlashSaleInventory.Service.Model.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> create(
            @Valid @RequestBody SaveProductRequest request
    ) {
        Product savedProduct = productService.create(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedProduct);
    }

    @GetMapping("/{sku}")
    public Product getBySku(@PathVariable String sku) {
        return productService.getBySku(sku);
    }
}